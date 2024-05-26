package cn.featherfly.common.db.mapping;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.Property;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.lang.reflect.ClassType;
import cn.featherfly.common.lang.reflect.Type;

/**
 * The Class SimpleStore.
 *
 * @author zhongj
 */
public abstract class AbstractStore implements Store {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The java to sql type map. */
    private Map<Type<?>, JavaToSqlTypeRegister<?>> javaToSqlTypeRegisterMap = new HashMap<>(0);

    /** The sql type to java map. */
    private Map<SQLType, SqlTypeToJavaRegister<?>> sqlTypeToJavaRegisterMap = new HashMap<>(0);

    /**
     * Gets the java sql type mappers.
     *
     * @return the java sql type mappers
     */
    protected abstract Collection<JavaSqlTypeMapper<Object>> getJavaSqlTypeMappers();

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(JavaToSqlTypeRegister<?> register) {
        ClassType<?> type = new ClassType<>(register.getJavaType());
        JavaToSqlTypeRegister<?> oldRegister = javaToSqlTypeRegisterMap.get(type);
        if (oldRegister != null) {
            throw new JdbcMappingException("#java.type.registed", new Object[] { type.getType().getName(),
                oldRegister.getClass().getName(), oldRegister.getSqlType().getName(), register.getClass().getName() });
        }
        javaToSqlTypeRegisterMap.put(type, register);
        logger.debug("regist java type {} with sql type {}", type.getType().getName(), register.getSqlType().getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(SqlTypeToJavaRegister<?> register) {
        SqlTypeToJavaRegister<?> oldRegister = sqlTypeToJavaRegisterMap.get(register.getSqlType());
        if (oldRegister != null) {
            throw new JdbcMappingException("#sql.type.registed", new Object[] { oldRegister.getSqlType().getName(),
                oldRegister.getClass().getName(), oldRegister.getJavaType().getName(), register.getClass().getName() });
        }
        sqlTypeToJavaRegisterMap.put(register.getSqlType(), register);
        logger.debug("regist java type {} with sql type {}", register.getJavaType().getName(),
            register.getSqlType().getName());
    }

    /**
     * Gets the sql type.
     *
     * @param <E> the element type
     * @param javaType the java type
     * @return the sql type
     */
    @Override
    public <E> SQLType getSqlType(Class<E> javaType) {
        return getSqlType(new ClassType<>(javaType));
    }

    /**
     * Gets the sql type.
     *
     * @param <E> the element type
     * @param javaType the java type
     * @return the sql type
     */
    @Override
    public <E> SQLType getSqlType(Type<E> javaType) {
        //            for (JavaSqlTypeMapper<?> javaSqlTypeMapper : javaSqlTypeMappers) {
        //                JavaSqlTypeMapper<Object> mapper = (JavaSqlTypeMapper<Object>) javaSqlTypeMapper;
        //                if (mapper.support((GenericType<Object>) javaType)) {
        //                    @SuppressWarnings({ "rawtypes" })
        //                    SQLType sqlType = javaSqlTypeMapper.getSqlType((GenericType) javaType);
        //                    if (sqlType != null) {
        //                        return sqlType;
        //                    }
        //                }
        //            }
        JavaToSqlTypeRegister<?> register = javaToSqlTypeRegisterMap.get(javaType);
        if (register != null) {
            return register.getSqlType();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E> Class<E> getJavaType(SQLType sqlType) {
        // 获取javaType不需要用映射来做
        //            for (JavaSqlTypeMapper<?> sqlTypeToJavaMapper : javaSqlTypeMappers) {
        //                if (sqlTypeToJavaMapper.support(sqlType, null, null)) { // TODO 需要测试是否如此
        //                    Class<?> type = sqlTypeToJavaMapper.getJavaType(sqlType);
        //                    if (type != null) {
        //                        return (Class<E>) type;
        //                    }
        //                }
        //            }
        SqlTypeToJavaRegister<?> register = sqlTypeToJavaRegisterMap.get(sqlType);
        if (register != null) {
            return (Class<E>) register.getJavaType();
        }
        return null;
    }

    /**
     * Sets the.
     *
     * @param <E> the element type
     * @param prep the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @return true, if successful
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue) {
        if (columnValue == null) {
            JdbcUtils.setParameter(prep, columnIndex, columnValue);
            return true;
        } else {
            return _set(prep, columnIndex, columnValue, new ClassType<>((Class<E>) columnValue.getClass()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue, Property<?, E> javaType) {
        return _set(prep, columnIndex, columnValue, javaType);
    }

    /**
     * {@inheritDoc}
     */
    //    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <E> boolean _set(PreparedStatement prep, int columnIndex, E columnValue, Type<E> javaType) {
        if (columnValue == null) {
            JdbcUtils.setParameter(prep, columnIndex, (E) null);
            return true;
        }
        for (JavaSqlTypeMapper<?> javaSqlTypeMapper : getJavaSqlTypeMappers()) {
            if (javaSqlTypeMapper.support((Type) javaType)) {
                logger.debug("set value valueType[{}] type[{}] with mapper {}", javaType.getClass().getSimpleName(),
                    javaType.getType().getName(), javaSqlTypeMapper.getClass().getName());
                ((JavaSqlTypeMapper<Object>) javaSqlTypeMapper).set(prep, columnIndex, columnValue);
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Optional<E> get(ResultSet rs, int columnIndex, Class<E> javaType) {
        return _get(rs, columnIndex, new ClassType<>(javaType));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Optional<E> get(CallableStatement call, int paramIndex, Class<E> javaType) {
        return _get(call, paramIndex, new ClassType<>(javaType));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Optional<E> get(ResultSet rs, int columnIndex, Property<?, E> javaType) {
        return _get(rs, columnIndex, javaType);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <E> Optional<E> _get(CallableStatement call, int paramIndex, Type<E> javaType) {
        SQLType sqlType = JdbcUtils.getCallableSQLType(call, paramIndex);
        for (JavaSqlTypeMapper<?> sqlTypeToJavaMapper : getJavaSqlTypeMappers()) {
            if (sqlTypeToJavaMapper.support((Type) javaType)) {
                logger.debug("get value from [{}] with mapper {}", sqlType, sqlTypeToJavaMapper.getClass().getName());
                return Optional.ofNullable((E) sqlTypeToJavaMapper.get(call, paramIndex));
            }
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <E> Optional<E> _get(ResultSet rs, int columnIndex, Type<E> javaType) {
        SQLType sqlType = JdbcUtils.getResultSetType(rs, columnIndex);
        for (JavaSqlTypeMapper<?> sqlTypeToJavaMapper : getJavaSqlTypeMappers()) {
            String tableName = JdbcUtils.getTableName(rs, columnIndex);
            String columnName = JdbcUtils.getColumnName(rs, columnIndex);
            if (sqlTypeToJavaMapper.support(sqlType, tableName, columnName)
                && sqlTypeToJavaMapper.support((Type) javaType)) {
                logger.debug("get value from {}.{} [{}] with mapper {}", tableName, columnName, sqlType,
                    sqlTypeToJavaMapper.getClass().getName());
                return Optional.ofNullable((E) sqlTypeToJavaMapper.get(rs, columnIndex));
            }
        }
        return null;
    }

}
