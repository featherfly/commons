
package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;

import cn.featherfly.common.db.metadata.SqlType;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.repository.mapping.MappingException;

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
    private DefaultSqlTypeMapping defaultSqlTypeMapping;

    /** The global store. */
    private Store globalStore;

    /** The type store. */
    private Map<Class<?>, Store> typeStore;

    /**
     * Instantiates a new sql type mapping manager.
     */
    public SqlTypeMappingManager() {
        defaultSqlTypeMapping = new DefaultSqlTypeMapping();
        globalStore = new Store();
    }

    /**
     * Regist.
     *
     * @param register the register
     * @return the sql type mapping manager
     */
    public SqlTypeMappingManager regist(JavaToSqlTypeRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(register, "register");
        return _regist(null, register);
    }

    /**
     * Regist.
     *
     * @param entityType the entity type
     * @param register   the register
     * @return the sql type mapping manager
     */
    public SqlTypeMappingManager regist(Class<?> entityType, JavaToSqlTypeRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(register, "register");
        return _regist(entityType, register);
    }

    /**
     * Regist.
     *
     * @param entityType the entity type
     * @param register   the register
     * @return the sql type mapping manager
     */
    private SqlTypeMappingManager _regist(Class<?> entityType, JavaToSqlTypeRegister<? extends Serializable> register) {
        if (entityType == null) {
            globalStore.put(register);
            return this;
        } else {
            Store store = typeStore.get(entityType);
            if (store != null) {
                store.put(register);
            }
        }
        return this;
    }

    /**
     * Regist.
     *
     * @param register the register
     * @return the sql type mapping manager
     */
    public SqlTypeMappingManager regist(SqlTypeToJavaRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(register, "register");
        globalStore.put(register);
        return this;
    }

    /**
     * Regist.
     *
     * @param entityType the entity type
     * @param register   the register
     * @return the sql type mapping manager
     */
    public SqlTypeMappingManager regist(Class<?> entityType, SqlTypeToJavaRegister<? extends Serializable> register) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(register, "register");
        Store store = typeStore.get(entityType);
        if (store == null) {
            store = new Store();
            typeStore.put(entityType, store);
        }
        store.put(register);
        return this;
    }

    /**
     * Gets the sql type.
     *
     * @param <E>      the element type
     * @param javaType the java type
     * @return the sql type
     */
    public <E extends Serializable> SqlType getSqlType(Class<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SqlType sqlType = globalStore.getSqlType(javaType);
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
    public <E extends Serializable> SqlType getSqlType(Class<E> entityType, Class<E> javaType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SqlType sqlType = null;
        Store store = typeStore.get(entityType);
        if (store != null) {
            sqlType = store.getSqlType(javaType);
        }
        if (sqlType == null) {
            sqlType = defaultSqlTypeMapping.getSqlType(javaType);
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
    public <E extends Serializable> Class<E> getJavaType(SqlType sqlType) {
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
    public <E extends Serializable> Class<E> getJavaType(Class<E> entityType, SqlType sqlType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(sqlType, "sqlType");
        Class<E> javaType = null;
        Store store = typeStore.get(entityType);
        if (store != null) {
            javaType = store.getJavaType(sqlType);
        }
        if (javaType == null) {
            javaType = defaultSqlTypeMapping.getJavaType(sqlType);
        }
        return javaType;
    }

    /**
     * The Class Store.
     */
    private class Store {

        /** The java to sql type map. */
        private Map<Class<? extends Serializable>, JavaToSqlTypeRegister<? extends Serializable>> javaToSqlTypeMap = new HashMap<>();

        private Map<Class<? extends Serializable>, JavaToSqlTypeMapper<? extends Serializable>> javaToSqlTypeMappers = new HashMap<>();

        /** The sql type to java map. */
        private Map<SqlType, Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>>> sqlTypeToJavaMap = new HashMap<>();

        /**
         * Put.
         *
         * @param register the register
         */
        private void put(JavaToSqlTypeRegister<? extends Serializable> register) {
            Class<? extends Serializable> type = ClassUtils.getSuperClassGenricType(register.getClass());
            JavaToSqlTypeRegister<? extends Serializable> oldRegister = null;
            if ((oldRegister = javaToSqlTypeMap.get(type)) != null) {
                throw new MappingException(String.format("java type %s already regist with register %s for sql type %s",
                        type.getName(), oldRegister.getClass().getName(), oldRegister.getSqlType().name()));
            }
            javaToSqlTypeMap.put(type, register);
            logger.debug("regist java type {} with sql type {}", type.getName(), register.getSqlType().name());
        }

        /**
         * Put.
         *
         * @param register the register
         */
        private void put(SqlTypeToJavaRegister<? extends Serializable> register) {
            Class<? extends Serializable> type = ClassUtils.getSuperClassGenricType(register.getClass());
            Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>> oldRegister = null;
            if ((oldRegister = sqlTypeToJavaMap.get(register.getSqlType())) != null) {
                throw new MappingException(String.format("sql type %s already regist with register %s for java type %s",
                        oldRegister.get0().getSqlType().name(), oldRegister.get0().getClass().getName(),
                        oldRegister.get1().getName()));
            }
            sqlTypeToJavaMap.put(register.getSqlType(), Tuples.of(register, type));
            logger.debug("regist java type {} with sql type {}", type.getName(), register.getSqlType().name());
        }

        /**
         * Gets the sql type.
         *
         * @param <E>      the element type
         * @param javaType the java type
         * @return the sql type
         */
        private <E extends Serializable> SqlType getSqlType(Class<E> javaType) {
            JavaToSqlTypeRegister<? extends Serializable> register = javaToSqlTypeMap.get(javaType);
            if (register != null) {
                return register.getSqlType();
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
        private <E extends Serializable> Class<E> getJavaType(SqlType sqlType) {
            Tuple2<SqlTypeToJavaRegister<? extends Serializable>, Class<? extends Serializable>> tuple = sqlTypeToJavaMap
                    .get(sqlType);
            if (tuple != null) {
                return (Class<E>) tuple.get1();
            }
            return null;
        }
    }

}
