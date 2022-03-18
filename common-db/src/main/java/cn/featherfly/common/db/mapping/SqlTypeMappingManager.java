
package cn.featherfly.common.db.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanProperty;
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
    private Map<GenericType<?>, Store> typeStoreMap = new HashMap<>();

    /**
     * Instantiates a new sql type mapping manager.
     */
    public SqlTypeMappingManager() {
    }

    /**
     * Checks if is enum with ordinal.
     *
     * @return true, if is enum with ordinal
     * @see cn.featherfly.common.db.mapping.DefaultSqlTypeMapping#isEnumWithOrdinal()
     */
    public boolean isEnumWithOrdinal() {
        return defaultSqlTypeMapping.isEnumWithOrdinal();
    }

    /**
     * Sets the enum with ordinal.
     *
     * @param enumWithOrdinal the new enum with ordinal
     * @see cn.featherfly.common.db.mapping.DefaultSqlTypeMapping#setEnumWithOrdinal(boolean)
     */
    public void setEnumWithOrdinal(boolean enumWithOrdinal) {
        defaultSqlTypeMapping.setEnumWithOrdinal(enumWithOrdinal);
    }

    /**
     * Sets the enum ordinal type.
     *
     * @param enumOrdinalType the new enum ordinal type
     * @see cn.featherfly.common.db.mapping.DefaultSqlTypeMapping#setEnumOrdinalType(java.sql.SQLType)
     */
    public void setEnumOrdinalType(SQLType enumOrdinalType) {
        defaultSqlTypeMapping.setEnumOrdinalType(enumOrdinalType);
    }

    /**
     * Gets the enum ordinal type.
     *
     * @return the enum ordinal type
     * @see cn.featherfly.common.db.mapping.DefaultSqlTypeMapping#getEnumOrdinalType()
     */
    public SQLType getEnumOrdinalType() {
        return defaultSqlTypeMapping.getEnumOrdinalType();
    }

    /**
     * Regist java to sqltype.
     *
     * @param javaToSqlTypeRegister the java to sql type register
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(JavaToSqlTypeRegister<? extends Object> javaToSqlTypeRegister) {
        AssertIllegalArgument.isNotNull(javaToSqlTypeRegister, "register");
        globalStore.put(javaToSqlTypeRegister);
        return this;
    }

    /**
     * Regist sqltype to java.
     *
     * @param sqlTypeToJavaRegister the sql type to java register
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(SqlTypeToJavaRegister<? extends Object> sqlTypeToJavaRegister) {
        AssertIllegalArgument.isNotNull(sqlTypeToJavaRegister, "register");
        globalStore.put(sqlTypeToJavaRegister);
        return this;
    }

    /**
     * Regist global javaSqlTypeMapper.
     *
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(JavaSqlTypeMapper<? extends Object> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
        globalStore.add(javaSqlTypeMapper);
        return this;
    }

    /**
     * Regist javaSqlTypeMapper for entityType.
     *
     * @param entityType        the java type
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(Class<?> entityType, JavaSqlTypeMapper<? extends Object> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        return regist(new GenericClass<>(entityType), javaSqlTypeMapper);
    }

    /**
     * Regist javaSqlTypeMapper for entityType.
     *
     * @param entityType        the java type
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(GenericType<?> entityType,
            JavaSqlTypeMapper<? extends Object> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
        Store store = getStoreForRegist(entityType);
        store.add(javaSqlTypeMapper);
        return this;
    }

    /**
     * Gets the sql type.
     *
     * @param <T>      the generic type
     * @param javaType the java type
     * @return the sql type
     */
    public <T> SQLType getSqlType(Class<T> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SQLType sqlType = globalStore.getSqlType(javaType);
        if (sqlType == null) {
            sqlType = defaultSqlTypeMapping.getSqlType(javaType);
        }
        // TODO 这里可以加入javaType SqlType map用于缓存结果 Map<Class<T>, SQLType>
        return sqlType;
    }

    /**
     * Gets the sql type.
     *
     * @param <E>        the element type
     * @param <T>        the generic type
     * @param entityType the entity type
     * @param javaType   the java type
     * @return the sql type
     */
    public <E, T> SQLType getSqlType(Class<E> entityType, Class<T> javaType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        SQLType sqlType = null;
        Store store = getStore(new GenericClass<>(entityType));
        if (store != null) {
            sqlType = store.getSqlType(javaType);
        }
        if (sqlType == null) {
            sqlType = getSqlType(javaType);
        }
        // TODO 这里可以加入entityType  javaType SqlType map用于缓存结果 Map<Class<E>, Map<Class<T>, SQLType>>
        return sqlType;
    }

    /**
     * Gets the sql type.
     *
     * @param <T>          the generic type
     * @param beanProperty the bean property
     * @return the sql type
     */
    public <T> SQLType getSqlType(BeanProperty<T> beanProperty) {
        return getSqlType(beanProperty.getOwnerType(), beanProperty.getType());
    }

    /**
     * Gets the java type.
     *
     * @param <T>     the generic type
     * @param sqlType the sql type
     * @return the java type
     */
    public <T> Class<T> getJavaType(SQLType sqlType) {
        AssertIllegalArgument.isNotNull(sqlType, "sqlType");
        Class<T> javaType = globalStore.getJavaType(sqlType);
        if (javaType == null) {
            javaType = defaultSqlTypeMapping.getJavaType(sqlType);
        }
        // TODO 这里可以加入javaType SqlType map用于缓存结果 Map<SQLType, Class<T>>
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
    public <E> Class<E> getJavaType(Class<E> entityType, SQLType sqlType) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(sqlType, "sqlType");
        Class<E> javaType = null;
        Store store = getStore(new GenericClass<>(entityType));
        if (store != null) {
            javaType = store.getJavaType(sqlType);
        }
        if (javaType == null) {
            javaType = getJavaType(sqlType);
        }
        // TODO 这里可以加入entityType  SqlType javaType map用于缓存结果 Map<Class<E>, Map<SQLType, Class<T>>>
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
    public <E> void set(PreparedStatement prep, int columnIndex, E columnValue) {
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        // 没有分组信息，直接使用全局映射
        //        if (columnValue != null) {
        //            GenericType<?> gt = new GenericClass<>(columnValue.getClass());
        //            Store store = getStore(gt);
        //            if (store != null && store.set(prep, columnIndex, columnValue)) {
        //                return;
        //            }
        //        }
        if (globalStore.set(prep, columnIndex, columnValue)) {
            return;
        }
        JdbcUtils.setParameter(prep, columnIndex, columnValue);
    }

    /**
     * Sets the.
     *
     * @param <E>         the element type
     * @param prep        the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @param javaType    the java type
     */
    public <E> void set(PreparedStatement prep, int columnIndex, E columnValue, GenericType<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        Store store = getStore(javaType);
        if (store != null && store.set(prep, columnIndex, columnValue, javaType)) {
            return;
        }
        // 如果是对象属性，再次查找属性所属类型的空间
        if (javaType instanceof BeanProperty) {
            store = getStore(new GenericClass<>(((BeanProperty<E>) javaType).getOwnerType()));
            if (store != null && store.set(prep, columnIndex, columnValue, javaType)) {
                return;
            }
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
    public <E> E get(ResultSet rs, int columnIndex, GenericType<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(rs, "ResultSet");
        Store store = getStore(javaType);
        E e = null;
        if (store != null && (e = store.get(rs, columnIndex, javaType)) != null) {
            return e;
        }
        // 如果是对象属性，再次查找属性所属类型的空间
        if (javaType instanceof BeanProperty) {
            store = getStore(new GenericClass<>(((BeanProperty<E>) javaType).getOwnerType()));
            if (store != null && (e = store.get(rs, columnIndex, javaType)) != null) {
                return e;
            }
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
    public <E> E get(ResultSet rs, String columnName, GenericType<E> javaType) {
        AssertIllegalArgument.isNotEmpty(columnName, "name");
        int index = JdbcUtils.getColumnIndex(rs, columnName);
        return get(rs, index, javaType);
    }

    //    private Store getStore(Class<?> entityType) {
    //        Store store = getStore(entityType);
    //        if (store == null) {
    //            store = new Store();
    //            typeStoreMap.put(entityType, store);
    //        }
    //        return store;
    //    }

    /**
     * Gets the store for regist.
     *
     * @param <E>        the element type
     * @param entityType the entity type
     * @return the store for regist
     */
    private <E> Store getStoreForRegist(GenericType<E> entityType) {
        Store store = getStore(entityType);
        if (store == null) {
            store = new Store();
            typeStoreMap.put(entityType, store);
        }
        return store;
    }

    /**
     * Gets the store.
     *
     * @param <E>        the element type
     * @param entityType the entity type
     * @return the store
     */
    private <E> Store getStore(GenericType<E> entityType) {
        return typeStoreMap.get(entityType);
    }

    /** The java to sql type map. */
    private Map<GenericType<? extends Object>, JavaToSqlTypeRegister<? extends Object>> javaToSqlTypeRegisterMap = new HashMap<>();

    /** The sql type to java map. */
    private Map<SQLType, SqlTypeToJavaRegister<? extends Object>> sqlTypeToJavaRegisterMap = new HashMap<>();

    /**
     * The Class Store.
     */
    private class Store {

        /** The java to sql type mappers. */
        private List<JavaSqlTypeMapper<? extends Object>> javaSqlTypeMappers = new ArrayList<>();

        /**
         * The sql type to java mappers.
         *
         * @param mapper the mapper
         */
        //        private List<JavaSqlTypeMapper<? extends Object>> javaToSqlTypeMappers = new ArrayList<>();

        /**
         * Adds the.
         *
         * @param mapper the mapper
         */
        private void add(JavaSqlTypeMapper<? extends Object> mapper) {
            javaSqlTypeMappers.add(mapper);
        }

        /**
         * Put.
         *
         * @param register the register
         */
        private void put(JavaToSqlTypeRegister<? extends Object> register) {
            GenericClass<? extends Object> type = new GenericClass<>(register.getJavaType());
            JavaToSqlTypeRegister<? extends Object> oldRegister = javaToSqlTypeRegisterMap.get(type);
            if (oldRegister != null) {
                throw new JdbcMappingException("#java.type.registed",
                        new Object[] { type.getType().getName(), oldRegister.getClass().getName(),
                                oldRegister.getSqlType().getName(), register.getClass().getName() });
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
        private void put(SqlTypeToJavaRegister<? extends Object> register) {
            SqlTypeToJavaRegister<? extends Object> oldRegister = sqlTypeToJavaRegisterMap.get(register.getSqlType());
            if (oldRegister != null) {
                throw new JdbcMappingException("#sql.type.registed",
                        new Object[] { oldRegister.getSqlType().getName(), oldRegister.getClass().getName(),
                                oldRegister.getJavaType().getName(), register.getClass().getName() });
            }
            sqlTypeToJavaRegisterMap.put(register.getSqlType(), register);
            logger.debug("regist java type {} with sql type {}", register.getJavaType().getName(),
                    register.getSqlType().getName());
        }

        /**
         * Gets the sql type.
         *
         * @param <E>      the element type
         * @param javaType the java type
         * @return the sql type
         */
        private <E> SQLType getSqlType(Class<E> javaType) {
            return getSqlType(new GenericClass<>(javaType));
        }

        /**
         * Gets the sql type.
         *
         * @param <E>      the element type
         * @param javaType the java type
         * @return the sql type
         */
        private <E> SQLType getSqlType(GenericType<E> javaType) {
            //            for (JavaSqlTypeMapper<? extends Object> javaSqlTypeMapper : javaSqlTypeMappers) {
            //                JavaSqlTypeMapper<Object> mapper = (JavaSqlTypeMapper<Object>) javaSqlTypeMapper;
            //                if (mapper.support((GenericType<Object>) javaType)) {
            //                    @SuppressWarnings({ "rawtypes" })
            //                    SQLType sqlType = javaSqlTypeMapper.getSqlType((GenericType) javaType);
            //                    if (sqlType != null) {
            //                        return sqlType;
            //                    }
            //                }
            //            }
            JavaToSqlTypeRegister<? extends Object> register = javaToSqlTypeRegisterMap.get(javaType);
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
        private <E> Class<E> getJavaType(SQLType sqlType) {
            // 获取javaType不需要用映射来做
            //            for (JavaSqlTypeMapper<? extends Object> sqlTypeToJavaMapper : javaSqlTypeMappers) {
            //                if (sqlTypeToJavaMapper.support(sqlType, null, null)) { // TODO 需要测试是否如此
            //                    Class<? extends Object> type = sqlTypeToJavaMapper.getJavaType(sqlType);
            //                    if (type != null) {
            //                        return (Class<E>) type;
            //                    }
            //                }
            //            }
            SqlTypeToJavaRegister<? extends Object> register = sqlTypeToJavaRegisterMap.get(sqlType);
            if (register != null) {
                return (Class<E>) register.getJavaType();
            }
            return null;
        }

        /**
         * Sets the.
         *
         * @param <E>         the element type
         * @param prep        the prep
         * @param columnIndex the column index
         * @param columnValue the column value
         * @return true, if successful
         */
        @SuppressWarnings("unchecked")
        public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue) {
            if (columnValue == null) {
                JdbcUtils.setParameter(prep, columnIndex, columnValue);
                return true;
            } else {
                return set(prep, columnIndex, columnValue, new GenericClass<>((Class<E>) columnValue.getClass()));
            }
        }

        /**
         * Sets the.
         *
         * @param <E>         the element type
         * @param prep        the prep
         * @param columnIndex the column index
         * @param columnValue the column value
         * @param javaType    the java type
         * @return true, if successful
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue, GenericType<E> javaType) {
            if (columnValue == null) {
                JdbcUtils.setParameter(prep, columnIndex, columnValue);
                return true;
            }
            for (JavaSqlTypeMapper<? extends Object> javaSqlTypeMapper : javaSqlTypeMappers) {
                if (javaSqlTypeMapper.support((GenericType) javaType)) {
                    logger.debug("set value javatype {}[{}] with mapper {}", javaType.getClass().getSimpleName(),
                            javaType.getType().getName(), javaSqlTypeMapper.getClass().getName());
                    ((JavaSqlTypeMapper<Object>) javaSqlTypeMapper).set(prep, columnIndex, columnValue);
                    return true;
                }
            }
            return false;
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
        public <E> E get(ResultSet rs, int columnIndex, GenericType<E> javaType) {
            SQLType sqlType = JdbcUtils.getResultSQLType(rs, columnIndex);
            for (JavaSqlTypeMapper<? extends Object> sqlTypeToJavaMapper : javaSqlTypeMappers) {
                JavaSqlTypeMapper<Object> mapper = (JavaSqlTypeMapper<Object>) sqlTypeToJavaMapper;
                String tableName = JdbcUtils.getTableName(rs, columnIndex);
                String columnName = JdbcUtils.getColumnName(rs, columnIndex);
                if (mapper.support(sqlType, tableName, columnName) && mapper.support((GenericType<Object>) javaType)) {
                    logger.debug("get value from {}.{} [{}] with mapper {}", tableName, columnName, sqlType.toString(),
                            mapper.getClass().getName());
                    return (E) mapper.get(rs, columnIndex);
                }
            }
            return null;
        }
    }
}
