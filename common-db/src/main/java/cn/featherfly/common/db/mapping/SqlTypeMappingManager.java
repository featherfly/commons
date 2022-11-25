
package cn.featherfly.common.db.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * SqlTypeMappingManager.
 *
 * @author zhongj
 */
public class SqlTypeMappingManager {

    /** The logger. */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The default sql type mapping. */
    private DefaultSqlTypeMapping defaultSqlTypeMapping = new DefaultSqlTypeMapping();

    /** The global store. */
    private SimpleStore globalStore = new SimpleStore();

    private Map<Class<?>, TypeStore> entityTypeStoreMap = new HashMap<>(0);

    private Map<Class<?>, SimpleStore> entityStoreMap = new HashMap<>(0);

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
    public SqlTypeMappingManager regist(JavaToSqlTypeRegister<?> javaToSqlTypeRegister) {
        AssertIllegalArgument.isNotNull(javaToSqlTypeRegister, "register");
        globalStore.add(javaToSqlTypeRegister);
        return this;
    }

    /**
     * Regist sqltype to java.
     *
     * @param sqlTypeToJavaRegister the sql type to java register
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(SqlTypeToJavaRegister<?> sqlTypeToJavaRegister) {
        AssertIllegalArgument.isNotNull(sqlTypeToJavaRegister, "register");
        globalStore.add(sqlTypeToJavaRegister);
        return this;
    }

    /**
     * Regist global javaSqlTypeMapper.
     *
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(JavaSqlTypeMapper<?> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
        globalStore.add(javaSqlTypeMapper);
        return this;
    }

    /**
     * Regist javaSqlTypeMapper for entityType.
     *
     * @param entityType        the java entity type
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(Class<?> entityType, JavaSqlTypeMapper<?> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(entityType, "entityType");
        AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
        SimpleStore store = getStoreForRegist(entityType);
        store.add(javaSqlTypeMapper);
        return this;
    }

    //        /**
    //         * Regist javaSqlTypeMapper for entityType.
    //         *
    //         * @param entityType        the java entity type
    //         * @param javaSqlTypeMapper the java sql type mapper
    //         * @return SqlTypeMappingManager
    //         */
    //        public SqlTypeMappingManager regist(Type<?> entityType, JavaSqlTypeMapper<?> javaSqlTypeMapper) {
    //            AssertIllegalArgument.isNotNull(entityType, "entityType");
    //            AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
    //            GroupableStore store = getStoreForRegist(entityType.getClass());
    //            store.put(entityType, javaSqlTypeMapper);
    //            return this;
    //        }

    /**
     * Regist javaSqlTypeMapper for entityType.
     *
     * @param beanProperty      the bean property
     * @param javaSqlTypeMapper the java sql type mapper
     * @return SqlTypeMappingManager
     */
    public SqlTypeMappingManager regist(BeanProperty<?> beanProperty, JavaSqlTypeMapper<?> javaSqlTypeMapper) {
        AssertIllegalArgument.isNotNull(beanProperty, "beanProperty");
        AssertIllegalArgument.isNotNull(javaSqlTypeMapper, "mapper");
        // 属性从属与类型，所有用属性定义的类型组
        TypeStore store = getStoreForRegist(beanProperty);
        store.put(beanProperty, javaSqlTypeMapper);
        return this;
    }

    /**
     * Gets the java sql type mapper.
     *
     * @param property the property
     * @return the java sql type mapper
     */
    public JavaSqlTypeMapper<?> getJavaSqlTypeMapper(BeanProperty<?> property) {
        TypeStore store = getStore(property);
        if (store == null) {
            return null;
        }
        return store.getJavaSqlTypeMapper(property);
    }

    //    public JavaSqlTypeMapper<?> getJavaSqlTypeMapper(Class<?> type) {
    //        //        IMPLSOON 后续来实现
    //        return null;
    //    }

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
        // ENHANCE 这里可以加入javaType SqlType map用于缓存结果 Map<Class<T>, SQLType>
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
        Store store = getStore(entityType);
        if (store != null) {
            sqlType = store.getSqlType(javaType);
        }
        if (sqlType == null) {
            sqlType = getSqlType(javaType);
        }
        // ENHANCE 这里可以加入entityType  javaType SqlType map用于缓存结果 Map<Class<E>, Map<Class<T>, SQLType>>
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
        // ENHANCE 这里可以加入javaType SqlType map用于缓存结果 Map<SQLType, Class<T>>
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
        Store store = getStore(entityType);
        if (store != null) {
            javaType = store.getJavaType(sqlType);
        }
        if (javaType == null) {
            javaType = getJavaType(sqlType);
        }
        // ENHANCE 这里可以加入entityType  SqlType javaType map用于缓存结果 Map<Class<E>, Map<SQLType, Class<T>>>
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
        Object value = null;
        if (columnValue instanceof Optional) {
            value = ((Optional<?>) columnValue).orElse(null);
        } else {
            value = columnValue;
        }

        if (value != null && globalStore.set(prep, columnIndex, value)) {
            return;
        }

        JdbcUtils.setParameter(prep, columnIndex, value);
    }

    /**
     * Sets the.
     *
     * @param <V>         the value type
     * @param <E>         the element type
     * @param prep        the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @param entityType  the entity type
     */
    public <V, E> void set(PreparedStatement prep, int columnIndex, V columnValue, Class<E> entityType) {
        if (entityType == null) {
            set(prep, columnIndex, columnValue);
            return;
        }
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        Store store = getStore(entityType);
        if (store != null && store.set(prep, columnIndex, columnValue)) {
            return;
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
     * @param prep        the prep
     * @param columnIndex the column index
     * @param columnValue the column value
     * @param javaType    the java type
     */
    public <E> void set(PreparedStatement prep, int columnIndex, E columnValue, BeanProperty<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(prep, "PreparedStatement");
        Store store = getStore(javaType);
        // bean property 一定是注册在 bean class 对应的store内的，如果没有使用BeanProperty注册
        // 则使用bean property的类型去匹配该类型空间下注册的类型
        if (store != null
                && (store.set(prep, columnIndex, columnValue, javaType) || store.set(prep, columnIndex, columnValue))) {
            return;
        }
        // 再查找属性拥有者对象的存储空间对应的属性类型
        store = getStore(javaType.getOwnerType());
        if (store != null && store.set(prep, columnIndex, columnValue)) {
            return;
        }

        if (globalStore.set(prep, columnIndex, columnValue)) {
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
    public <E> E get(ResultSet rs, int columnIndex, Class<E> javaType) {
        AssertIllegalArgument.isNotNull(javaType, "javaType");
        AssertIllegalArgument.isNotNull(rs, "ResultSet");
        Store store = getStore(javaType);
        Optional<E> e = null;
        if (store != null && (e = store.get(rs, columnIndex, javaType)) != null) {
            return e.orElse(null);
        }
        if ((e = globalStore.get(rs, columnIndex, javaType)) != null) {
            return e.orElse(null);
        }
        return JdbcUtils.getResultSetValue(rs, columnIndex, javaType);
    }

    /**
     * Gets the.
     *
     * @param <E>          the element type
     * @param rs           the rs
     * @param columnIndex  the column index
     * @param beanProperty the bean property
     * @return the e
     */
    public <E> E get(ResultSet rs, int columnIndex, BeanProperty<E> beanProperty) {
        AssertIllegalArgument.isNotNull(beanProperty, "beanProperty");
        AssertIllegalArgument.isNotNull(rs, "ResultSet");
        Store store = getStore(beanProperty);
        Optional<E> e = null;
        if (store != null && (e = store.get(rs, columnIndex, beanProperty)) != null) {
            return e.orElse(null);
        }
        // 再查找属性拥有者对象的存储空间对应的属性类型
        store = getStore(beanProperty.getOwnerType());
        if (store != null && (e = store.get(rs, columnIndex, beanProperty.getType())) != null) {
            return e.orElse(null);
        }

        if ((e = globalStore.get(rs, columnIndex, beanProperty.getType())) != null) {
            return e.orElse(null);
        }
        return JdbcUtils.getResultSetValue(rs, columnIndex, beanProperty.getType());
    }

    /*
     * Gets the store for regist.
     */

    //    /*
    //     * Gets the store.
    //     */
    //    private <E> GroupableStore getStore(Type<E> entityType) {
    //        return getStore(entityType.getType());
    //    }

    //    private <E> TypeStore getStore(Type<E> javaType) {
    //        if (javaType instanceof BeanProperty) {
    //            return getStore((BeanProperty<E>) javaType);
    //        } else {
    //            return getStore(javaType.getType());
    //        }
    //    }

    private <E> SimpleStore getStoreForRegist(Class<E> entityType) {
        SimpleStore store = getStore(entityType);
        if (store == null) {
            store = new SimpleStore();
            entityStoreMap.put(entityType, store);
        }
        return store;
    }

    private <E> SimpleStore getStore(Class<E> entityType) {
        return entityStoreMap.get(entityType);
    }

    private <E> TypeStore getStoreForRegist(BeanProperty<E> entityType) {
        TypeStore store = getStore(entityType);
        if (store == null) {
            store = new TypeStore();
            entityTypeStoreMap.put(entityType.getOwnerType(), store);
        }
        return store;
    }

    private <E> TypeStore getStore(BeanProperty<E> property) {
        return entityTypeStoreMap.get(property.getOwnerType());
    }

    //    /**
    //     * The Class Store.
    //     */
    //    private class Store {
    //
    //        /** The java to sql type mappers. */
    //        private Set<JavaSqlTypeMapper<?>> javaSqlTypeMappers = new LinkedHashSet<>(0);
    //
    //        /** The java to sql type mappers with type. */
    //        private Map<Type<?>, JavaSqlTypeMapper<?>> javaSqlTypeMapperMap = new HashMap<>(0);
    //
    //        /** The java to sql type map. */
    //        private Map<Type<?>, JavaToSqlTypeRegister<?>> javaToSqlTypeRegisterMap = new HashMap<>(0);
    //
    //        /** The sql type to java map. */
    //        private Map<SQLType, SqlTypeToJavaRegister<?>> sqlTypeToJavaRegisterMap = new HashMap<>(0);
    //
    //        /**
    //         * The sql type to java mappers.
    //         *
    //         * @param mapper the mapper
    //         */
    //        //        private List<JavaSqlTypeMapper<?>> javaToSqlTypeMappers = new ArrayList<>();
    //
    //        /**
    //         * Adds the.
    //         *
    //         * @param mapper the mapper
    //         */
    //        private void add(JavaSqlTypeMapper<?> mapper) {
    //            javaSqlTypeMappers.add(mapper);
    //        }
    //
    //        /**
    //         * Adds the.
    //         *
    //         * @param mapper the mapper
    //         */
    //        private void put(Type<?> type, JavaSqlTypeMapper<?> mapper) {
    //            javaSqlTypeMapperMap.put(type, mapper);
    //        }
    //
    //        /**
    //         * Put.
    //         *
    //         * @param register the register
    //         */
    //        private void put(JavaToSqlTypeRegister<?> register) {
    //            ClassType<?> type = new ClassType<>(register.getJavaType());
    //            JavaToSqlTypeRegister<?> oldRegister = javaToSqlTypeRegisterMap.get(type);
    //            if (oldRegister != null) {
    //                throw new JdbcMappingException("#java.type.registed",
    //                        new Object[] { type.getType().getName(), oldRegister.getClass().getName(),
    //                                oldRegister.getSqlType().getName(), register.getClass().getName() });
    //            }
    //            javaToSqlTypeRegisterMap.put(type, register);
    //            logger.debug("regist java type {} with sql type {}", type.getType().getName(),
    //                    register.getSqlType().getName());
    //        }
    //
    //        /**
    //         * Put.
    //         *
    //         * @param register SqlTypeToJavaRegister
    //         */
    //        private void put(SqlTypeToJavaRegister<?> register) {
    //            SqlTypeToJavaRegister<?> oldRegister = sqlTypeToJavaRegisterMap.get(register.getSqlType());
    //            if (oldRegister != null) {
    //                throw new JdbcMappingException("#sql.type.registed",
    //                        new Object[] { oldRegister.getSqlType().getName(), oldRegister.getClass().getName(),
    //                                oldRegister.getJavaType().getName(), register.getClass().getName() });
    //            }
    //            sqlTypeToJavaRegisterMap.put(register.getSqlType(), register);
    //            logger.debug("regist java type {} with sql type {}", register.getJavaType().getName(),
    //                    register.getSqlType().getName());
    //        }
    //
    //        /**
    //         * Gets the sql type.
    //         *
    //         * @param <E>      the element type
    //         * @param javaType the java type
    //         * @return the sql type
    //         */
    //        private <E> SQLType getSqlType(Class<E> javaType) {
    //            return getSqlType(new ClassType<>(javaType));
    //        }
    //
    //        /**
    //         * Gets the sql type.
    //         *
    //         * @param <E>      the element type
    //         * @param javaType the java type
    //         * @return the sql type
    //         */
    //        private <E> SQLType getSqlType(Type<E> javaType) {
    //            //            for (JavaSqlTypeMapper<?> javaSqlTypeMapper : javaSqlTypeMappers) {
    //            //                JavaSqlTypeMapper<Object> mapper = (JavaSqlTypeMapper<Object>) javaSqlTypeMapper;
    //            //                if (mapper.support((GenericType<Object>) javaType)) {
    //            //                    @SuppressWarnings({ "rawtypes" })
    //            //                    SQLType sqlType = javaSqlTypeMapper.getSqlType((GenericType) javaType);
    //            //                    if (sqlType != null) {
    //            //                        return sqlType;
    //            //                    }
    //            //                }
    //            //            }
    //            JavaToSqlTypeRegister<?> register = javaToSqlTypeRegisterMap.get(javaType);
    //            if (register != null) {
    //                return register.getSqlType();
    //            }
    //            return null;
    //        }
    //
    //        /**
    //         * Gets the java type.
    //         *
    //         * @param <E>     the element type
    //         * @param sqlType the sql type
    //         * @return the java type
    //         */
    //        @SuppressWarnings("unchecked")
    //        private <E> Class<E> getJavaType(SQLType sqlType) {
    //            // 获取javaType不需要用映射来做
    //            //            for (JavaSqlTypeMapper<?> sqlTypeToJavaMapper : javaSqlTypeMappers) {
    //            //                if (sqlTypeToJavaMapper.support(sqlType, null, null)) { // TODO 需要测试是否如此
    //            //                    Class<?> type = sqlTypeToJavaMapper.getJavaType(sqlType);
    //            //                    if (type != null) {
    //            //                        return (Class<E>) type;
    //            //                    }
    //            //                }
    //            //            }
    //            SqlTypeToJavaRegister<?> register = sqlTypeToJavaRegisterMap.get(sqlType);
    //            if (register != null) {
    //                return (Class<E>) register.getJavaType();
    //            }
    //            return null;
    //        }
    //
    //        /**
    //         * Sets the.
    //         *
    //         * @param <E>         the element type
    //         * @param prep        the prep
    //         * @param columnIndex the column index
    //         * @param columnValue the column value
    //         * @return true, if successful
    //         */
    //        @SuppressWarnings("unchecked")
    //        public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue) {
    //            if (columnValue == null) {
    //                JdbcUtils.setParameter(prep, columnIndex, columnValue);
    //                return true;
    //            } else {
    //                return set(prep, columnIndex, columnValue, new ClassType<>((Class<E>) columnValue.getClass()));
    //            }
    //        }
    //
    //        /**
    //         * Sets the.
    //         *
    //         * @param <E>         the element type
    //         * @param prep        the prep
    //         * @param columnIndex the column index
    //         * @param columnValue the column value
    //         * @param javaType    the java type
    //         * @return true, if successful
    //         */
    //        @SuppressWarnings({ "unchecked", "rawtypes" })
    //        public <E> boolean set(PreparedStatement prep, int columnIndex, E columnValue, Type<E> javaType) {
    //            if (columnValue == null) {
    //                JdbcUtils.setParameter(prep, columnIndex, columnValue);
    //                return true;
    //            }
    //            for (JavaSqlTypeMapper<?> javaSqlTypeMapper : javaSqlTypeMappers) {
    //                if (javaSqlTypeMapper.support((Type) javaType)) {
    //                    logger.debug("set value javatype {}[{}] with mapper {}", javaType.getClass().getSimpleName(),
    //                            javaType.getType().getName(), javaSqlTypeMapper.getClass().getName());
    //                    ((JavaSqlTypeMapper<Object>) javaSqlTypeMapper).set(prep, columnIndex, columnValue);
    //                    return true;
    //                }
    //            }
    //            return false;
    //        }
    //
    //        /**
    //         * Gets the.
    //         *
    //         * @param <E>         the element type
    //         * @param rs          the rs
    //         * @param columnIndex the column index
    //         * @param javaType    the java type
    //         * @return the e
    //         */
    //        @SuppressWarnings("unchecked")
    //        public <E> E get(ResultSet rs, int columnIndex, Type<E> javaType) {
    //            SQLType sqlType = JdbcUtils.getResultSQLType(rs, columnIndex);
    //            for (JavaSqlTypeMapper<?> sqlTypeToJavaMapper : javaSqlTypeMappers) {
    //                JavaSqlTypeMapper<Object> mapper = (JavaSqlTypeMapper<Object>) sqlTypeToJavaMapper;
    //                String tableName = JdbcUtils.getTableName(rs, columnIndex);
    //                String columnName = JdbcUtils.getColumnName(rs, columnIndex);
    //                if (mapper.support(sqlType, tableName, columnName) && mapper.support((Type<Object>) javaType)) {
    //                    logger.debug("get value from {}.{} [{}] with mapper {}", tableName, columnName, sqlType.toString(),
    //                            mapper.getClass().getName());
    //                    return (E) mapper.get(rs, columnIndex);
    //                }
    //            }
    //            return null;
    //        }
    //    }
}
