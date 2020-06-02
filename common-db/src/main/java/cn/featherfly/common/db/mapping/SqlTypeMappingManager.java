
package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * <p>
 * SqlTypeMapping
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlTypeMappingManager {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The default sql type mapping. */
    private DefaultSqlTypeMapping defaultSqlTypeMapping = new DefaultSqlTypeMapping();

    /** The global store. */
    private Store globalStore = new Store();

    /** The type store map. */
    private Map<Class<?>, Store> typeStoreMap = new HashMap<>();

    /**
     * Instantiates a new sql type mapping manager.
     */
    public SqlTypeMappingManager() {
    }

    /**
     * Regist.
     *
     * @param register the register
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(JavaToSqlTypeRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(register, "register");
        globalStore.put(register);
        return this;
    }

    //    /**
    //     * Regist.
    //     *
    //     * @param entityType the entity type
    //     * @param register   the register
    //     * @return SqlTypeMappingManager
    //     */
    //    public SqlTypeMappingManager regist(Class<?> entityType, JavaToSqlTypeRegister<? extends Serializable> register) {
    //        AssertIllegalArgument.isNotNull(entityType, "entityType");
    //        AssertIllegalArgument.isNotNull(register, "register");
    //        Store store = getStore(entityType);
    //        store.put(register);
    //        return this;
    //    }

    /**
     * Regist.
     *
     * @param mapper the JavaToSqlTypeMapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(JavaSqlTypeMapper<? extends Serializable> mapper) {
        AssertIllegalArgument.isNotNull(mapper, "mapper");
        globalStore.add(mapper);
        return this;
    }

    /**
     * Regist.
     *
     * @param entityType the entity type
     * @param mapper     the mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(Class<?> entityType, JavaSqlTypeMapper<? extends Serializable> mapper) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(mapper, "mapper");
        Store store = getStore(entityType);
        store.add(mapper);
        return this;
    }

    /**
     * Regist.
     *
     * @param register the register
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(SqlTypeToJavaRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(register, "register");
        globalStore.put(register);
        return this;
    }

    //    /**
    //     * Regist.
    //     *
    //     * @param entityType the entity type
    //     * @param register   the register
    //     * @return SqlTypeMappingManager
    //     */
    //    public SqlTypeMappingManager regist(Class<?> entityType, SqlTypeToJavaRegister<? extends Serializable> register) {
    //        AssertIllegalArgument.isNotNull(entityType, "entityType");
    //        AssertIllegalArgument.isNotNull(register, "register");
    //        Store store = getStore(entityType);
    //        store.put(register);
    //        return this;
    //    }

    //    /**
    //     * Regist.
    //     *
    //     * @param mapper the SqlTypeToJavaMapper
    //     * @return SqlTypeMappingManager
    //     */
    //    public SqlTypeMappingManager regist(SqlTypeToJavaMapper<? extends Serializable> mapper) {
    //        AssertIllegalArgument.isNotNull(mapper, "mapper");
    //        globalStore.add(mapper);
    //        return this;
    //    }
    //
    //    /**
    //     * Regist.
    //     *
    //     * @param entityType the entity type
    //     * @param mapper     the SqlTypeToJavaMapper
    //     * @return SqlTypeMappingManager
    //     */
    //    public SqlTypeMappingManager regist(Class<?> entityType, SqlTypeToJavaMapper<? extends Serializable> mapper) {
    //        AssertIllegalArgument.isNotNull(entityType, "entityType");
    //        AssertIllegalArgument.isNotNull(mapper, "mapper");
    //        Store store = getStore(entityType);
    //        store.add(mapper);
    //        return this;
    //    }

    /**
     * Gets the sql type.
     *
     * @param <E>      the element type
     * @param javaType the java type
     * @return the sql type
     */
    public <E extends Serializable> SQLType getSqlType(Class<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SQLType sqlType = globalStore.getSqlType(javaType);
        if (sqlType == null) {
            sqlType = defaultSqlTypeMapping.getSqlType(javaType);
        }
        return sqlType;
    }

    /**
     * Gets the sql type.
     *
     * @param <E>        the element type
     * @param entityType the entity type
     * @param javaType   the java type
     * @return the sql type
     */
    public <E extends Serializable> SQLType getSqlType(Class<E> entityType, Class<E> javaType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SQLType sqlType = null;
        Store store = typeStoreMap.get(entityType);
        if (store != null) {
            sqlType = store.getSqlType(javaType);
        }
        if (sqlType == null) {
            sqlType = getSqlType(javaType);
        }
        return sqlType;
    }

    /**
     * Gets the java type.
     *
     * @param <E>     the element type
     * @param sqlType the sql type
     * @return the java type
     */
    public <E extends Serializable> Class<E> getJavaType(SQLType sqlType) {
        AssertIllegalArgument.isNotNull(sqlType, "sqlType");
        Class<E> javaType = globalStore.getJavaType(sqlType);
        if (javaType == null) {
            javaType = defaultSqlTypeMapping.getJavaType(sqlType);
        }
        return javaType;
    }

    /**
     * Gets the java type.
     *
     * @param <E>        the element type
     * @param entityType the entity type
     * @param sqlType    the sql type
     * @return the java type
     */
    public <E extends Serializable> Class<E> getJavaType(Class<E> entityType, SQLType sqlType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(sqlType, "sqlType");
        Class<E> javaType = null;
        Store store = typeStoreMap.get(entityType);
        if (store != null) {
            javaType = store.getJavaType(sqlType);
        }
        if (javaType == null) {
            javaType = getJavaType(sqlType);
        }
        return javaType;
    }

    /**
     * Sets the.
     *
     * @param <E>         the element type
     * @param prep        the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     */
    public <E extends Serializable> void set(PreparedStatement prep, int columnIndex, E columnValue) {
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        if (columnValue != null) {
            Store store = typeStoreMap.get(columnValue.getClass());
            if (store != null && store.set(prep, columnIndex, columnValue)) {
                return;
            }
        }
        if (globalStore.set(prep, columnIndex, columnValue)) {
            return;
        }
        JdbcUtils.setParameter(prep, columnIndex, columnValue);
    }

    /**
     * Sets the.
     *
     * @param <E>         the element type
     * @param columnValue the column value
     * @param columnIndex the column index
     * @param javaType    the java type
     * @param prep        the prep
     */
    public <E extends Serializable> void set(E columnValue, int columnIndex, GenericType<E> javaType,
            PreparedStatement prep) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        Store store = typeStoreMap.get(javaType.getType());
        if (store != null && store.set(prep, columnIndex, columnValue, javaType)) {
            return;
        }
        if (globalStore.set(prep, columnIndex, columnValue, javaType)) {
            return;
        }
        JdbcUtils.setParameter(prep, columnIndex, columnValue);
    }

    /**
     * Gets the.
     *
     * @param <E>         the element type
     * @param rs          the rs
     * @param columnIndex the column index
     * @param javaType    the java type
     * @return the e
     */
    @SuppressWarnings("unchecked")
    public <E extends Serializable> E get(ResultSet rs, int columnIndex, GenericType<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(rs, "ResultSet");
        Store store = typeStoreMap.get(javaType.getType());
        E e = null;
        if (store != null && (e = store.get(rs, columnIndex, javaType)) != null) {
            return e;
        }
        if ((e = globalStore.get(rs, columnIndex, javaType)) != null) {
            return e;
        }
        return (E) JdbcUtils.getResultSetValue(rs, columnIndex, javaType.getType());
    }

    /**
     * Gets the.
     *
     * @param <E>        the element type
     * @param rs         the rs
     * @param columnName the column name
     * @param javaType   the java type
     * @return the e
     */
    public <E extends Serializable> E get(ResultSet rs, String columnName, GenericType<E> javaType) {
        AssertIllegalArgument.isNotEmpty(columnName, "name");
        int index = JdbcUtils.getColumnIndex(rs, columnName);
        return get(rs, index, javaType);
    }

    /**
     * Gets the store.
     *
     * @param entityType the entity type
     * @return the store
     */
    private Store getStore(Class<?> entityType) {
        Store store = typeStoreMap.get(entityType);
        if (store == null) {
            store = new Store();
            typeStoreMap.put(entityType, store);
        }
        return store;
    }

    /**
     * The Class Store.
     */
    private class Store {

        /** The java to sql type map. */
        private Map<GenericType<? extends Serializable>, JavaToSqlTypeRegister<? extends Serializable>> javaToSqlTypeRegisterMap = new HashMap<>();

        /** The sql type to java map. */
        private Map<SQLType, Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>>> sqlTypeToJavaRegisterMap = new HashMap<>();

        /** The java to sql type mappers. */
        private List<JavaSqlTypeMapper<? extends Serializable>> javaSqlTypeMappers = new ArrayList<>();

        /** The sql type to java mappers. */
        //        private List<JavaSqlTypeMapper<? extends Serializable>> javaToSqlTypeMappers = new ArrayList<>();

        /**
         * Adds the.
         *
         * @param mapper the mapper
         */
        private void add(JavaSqlTypeMapper<? extends Serializable> mapper) {
            javaSqlTypeMappers.add(mapper);
        }

        //        /**
        //         * Adds the.
        //         *
        //         * @param mapper the mapper
        //         */
        //        private void add(SqlTypeToJavaMapper<? extends Serializable> mapper) {
        //            javaToSqlTypeMappers.add(mapper);
        //        }

        /**
         * Put.
         *
         * @param register the register
         */
        private void put(JavaToSqlTypeRegister<? extends Serializable> register) {
            GenericClass<? extends Serializable> type = new GenericClass<>(register.getJavaType());
            JavaToSqlTypeRegister<? extends Serializable> oldRegister = null;
            if ((oldRegister = javaToSqlTypeRegisterMap.get(type)) != null) {
                throw new JdbcMappingException("#java.type.registed", new Object[] { type.getType().getName(),
                        oldRegister.getClass().getName(), oldRegister.getSqlType().getName() });
            }
            javaToSqlTypeRegisterMap.put(type, register);
            logger.debug("regist java type {} with sql type {}", type.getType().getName(),
                    register.getSqlType().getName());
        }

        /**
         * Put.
         *
         * @param register SqlTypeToJavaRegister
         */
        private void put(SqlTypeToJavaRegister<? extends Serializable> register) {
            Class<? extends Serializable> type = register.getJavaType();
            Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>> oldRegister = null;
            if ((oldRegister = sqlTypeToJavaRegisterMap.get(register.getSqlType())) != null) {
                throw new JdbcMappingException("#sql.type.registed",
                        new Object[] { oldRegister.get0().getSqlType().getName(),
                                oldRegister.get0().getClass().getName(), oldRegister.get1().getName() });
            }
            sqlTypeToJavaRegisterMap.put(register.getSqlType(), Tuples.of(register, type));
            logger.debug("regist java type {} with sql type {}", type.getName(), register.getSqlType().getName());
        }

        /**
         * Gets the sql type.
         *
         * @param <E>      the element type
         * @param javaType the java type
         * @return the sql type
         */
        private <E extends Serializable> SQLType getSqlType(Class<E> javaType) {
            return getSqlType(new GenericClass<>(javaType));
        }

        /**
         * Gets the sql type.
         *
         * @param <E>      the element type
         * @param javaType the java type
         * @return the sql type
         */
        @SuppressWarnings("unchecked")
        private <E extends Serializable> SQLType getSqlType(GenericType<E> javaType) {
            for (JavaSqlTypeMapper<? extends Serializable> javaSqlTypeMapper : javaSqlTypeMappers) {
                JavaSqlTypeMapper<Serializable> mapper = (JavaSqlTypeMapper<Serializable>) javaSqlTypeMapper;
                if (mapper.support((GenericType<Serializable>) javaType)) {
                    @SuppressWarnings({ "rawtypes" })
                    SQLType sqlType = javaSqlTypeMapper.getSqlType((GenericType) javaType);
                    if (sqlType != null) {
                        return sqlType;
                    }
                }
            }
            JavaToSqlTypeRegister<? extends Serializable> register = javaToSqlTypeRegisterMap.get(javaType);
            if (register != null) {
                return register.getSqlType();
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        public <E extends Serializable> boolean set(PreparedStatement prep, int columnIndex, E columnValue) {
            if (columnValue == null) {
                JdbcUtils.setParameter(prep, columnIndex, columnValue);
                return true;
            } else {
                return set(prep, columnIndex, columnValue, new GenericClass<>((Class<E>) columnValue.getClass()));
            }
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public <E extends Serializable> boolean set(PreparedStatement prep, int columnIndex, E columnValue,
                GenericType<E> javaType) {
            if (columnValue == null) {
                JdbcUtils.setParameter(prep, columnIndex, columnValue);
                return true;
            }
            for (JavaSqlTypeMapper<? extends Serializable> javaSqlTypeMapper : javaSqlTypeMappers) {
                if (javaSqlTypeMapper.support((GenericType) javaType)) {
                    SQLType sqlType = javaSqlTypeMapper.getSqlType((GenericType) javaType);
                    if (sqlType != null) {
                        ((JavaSqlTypeMapper<Serializable>) javaSqlTypeMapper).set(prep, columnIndex, columnValue);
                        return true;
                    }
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        public <E extends Serializable> E get(ResultSet rs, int columnIndex, GenericType<E> javaType) {
            SQLType sqlType = JdbcUtils.getResultSQLType(rs, columnIndex);
            for (JavaSqlTypeMapper<? extends Serializable> sqlTypeToJavaMapper : javaSqlTypeMappers) {
                JavaSqlTypeMapper<Serializable> mapper = (JavaSqlTypeMapper<Serializable>) sqlTypeToJavaMapper;
                if (mapper.support(sqlType) && mapper.support((GenericType<Serializable>) javaType)) {
                    return (E) mapper.get(rs, columnIndex);
                }
            }
            return null;
        }

        /**
         * Gets the java type.
         *
         * @param <E>     the element type
         * @param sqlType the sql type
         * @return the java type
         */
        @SuppressWarnings("unchecked")
        private <E extends Serializable> Class<E> getJavaType(SQLType sqlType) {
            for (JavaSqlTypeMapper<? extends Serializable> sqlTypeToJavaMapper : javaSqlTypeMappers) {
                if (sqlTypeToJavaMapper.support(sqlType)) {
                    Class<? extends Serializable> type = sqlTypeToJavaMapper.getJavaType(sqlType);
                    if (type != null) {
                        return (Class<E>) type;
                    }
                }
            }
            Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>> tuple = sqlTypeToJavaRegisterMap
                    .get(sqlType);
            if (tuple != null) {
                return (Class<E>) tuple.get1();
            }
            return null;
        }
    }
}
