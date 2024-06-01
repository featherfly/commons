
package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.bean.PropertyAccessorFactory;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.PostgreSQLDialect;
import cn.featherfly.common.db.jpa.ColumnDefault;
import cn.featherfly.common.db.jpa.Comment;
import cn.featherfly.common.db.mapping.operator.BasicOperators;
import cn.featherfly.common.db.mapping.operator.DefaultTypesSqlTypeOperator;
import cn.featherfly.common.db.mapping.operator.EnumSqlTypeOperator;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.repository.id.IdGenerator;
import cn.featherfly.common.repository.id.IdGeneratorManager;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.ClassNameJpaConversion;
import cn.featherfly.common.repository.mapping.ClassNameUnderscoreConversion;
import cn.featherfly.common.repository.mapping.PrimaryKey;
import cn.featherfly.common.repository.mapping.PropertyMapping.Mode;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameJpaConversion;
import cn.featherfly.common.repository.mapping.PropertyNameUnderscoreConversion;

/**
 * AbstractMappingFactory.
 *
 * @author zhongj
 */
public abstract class AbstractJdbcMappingFactory implements JdbcMappingFactory {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The mapped types. */
    protected final Map<Class<?>, JdbcClassMapping<?>> mappedTypes = new HashMap<>();

    /** The metadata. */
    protected DatabaseMetadata metadata;

    /** The dialect. */
    protected Dialect dialect;

    /** The class name conversions. */
    protected List<ClassNameConversion> classNameConversions = new ArrayList<>();

    /** The property name conversions. */
    protected List<PropertyNameConversion> propertyNameConversions = new ArrayList<>();

    /** The sql type mapping manager. */
    protected final SqlTypeMappingManager sqlTypeMappingManager;

    /** The property accessor factory. */
    protected final PropertyAccessorFactory propertyAccessorFactory;

    /** The check mapping. */
    protected boolean checkMapping;

    /** The id generator manager. */
    protected final IdGeneratorManager idGeneratorManager;

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param propertyAccessorFactory the property accessor factory
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        PropertyAccessorFactory propertyAccessorFactory) {
        this(metadata, dialect, sqlTypeMappingManager, idGeneratorManager, null, null, propertyAccessorFactory);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata DatabaseMetadata
     * @param dialect dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param classNameConversions classNameConversions
     * @param propertyNameConversions propertyNameConversions
     * @param propertyAccessorFactory the property accessor factory
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions,
        PropertyAccessorFactory propertyAccessorFactory) {
        super();
        this.metadata = metadata;
        this.dialect = dialect;
        this.propertyAccessorFactory = propertyAccessorFactory;
        this.idGeneratorManager = idGeneratorManager;

        if (Lang.isEmpty(classNameConversions)) {
            this.classNameConversions.add(new ClassNameJpaConversion());
            this.classNameConversions.add(new ClassNameUnderscoreConversion());
        } else {
            this.classNameConversions.addAll(classNameConversions);
        }

        if (Lang.isEmpty(propertyNameConversions)) {
            this.propertyNameConversions.add(new PropertyNameJpaConversion());
            this.propertyNameConversions.add(new PropertyNameUnderscoreConversion());
        } else {
            this.propertyNameConversions.addAll(propertyNameConversions);
        }
        if (sqlTypeMappingManager == null) {
            this.sqlTypeMappingManager = new SqlTypeMappingManager();
        } else {
            this.sqlTypeMappingManager = sqlTypeMappingManager;
        }
    }

    /**
     * Sets the column mapping.
     *
     * @param <E> the element type
     * @param mapping the mapping
     * @param beanProperty the bean property
     */
    protected <E extends Serializable> void setColumnMapping(JdbcPropertyMapping mapping,
        BeanProperty<?, E> beanProperty) {
        //        boolean isPk = beanProperty.hasAnnotation(Id.class);
        Column column = beanProperty.getAnnotation(Column.class);
        setPropertyMapping(mapping, beanProperty);
        setJavaSqlTypeMapper(mapping, beanProperty);
        if (column != null) {
            mapping.setSize(
                ClassUtils.isParent(Number.class, beanProperty.getType()) ? column.precision() : column.length());
            mapping.setDecimalDigits(column.scale());
            mapping.setInsertable(column.insertable());
            mapping.setUpdatable(column.updatable());
            mapping.setUnique(column.unique());
            mapping.setNullable(column.nullable());
        } else {
            // @Column length的默认值
            if (beanProperty.getType() == String.class) {
                mapping.setSize(255);
            } else if (beanProperty.getType() == Double.class || beanProperty.getType() == Double.TYPE
                || beanProperty.getType() == Float.class || beanProperty.getType() == Float.TYPE
                || beanProperty.getType() == BigDecimal.class) {
                mapping.setSize(12);
                mapping.setDecimalDigits(2);
            }
        }
        if (mapping.getSize() == 0) {
            SQLType sqlType = sqlTypeMappingManager.getSqlType(beanProperty.getType());
            mapping.setSize(dialect.getDefaultSize(sqlType));
        }
        ColumnDefault columnDefault = beanProperty.getAnnotation(ColumnDefault.class);
        if (columnDefault != null) {
            mapping.setDefaultValue(columnDefault.value());
        }
        Comment comment = beanProperty.getAnnotation(Comment.class);
        if (comment != null) {
            mapping.setRemark(comment.value());
        }
        if (beanProperty.getType().isEnum()) {
            if (!sqlTypeMappingManager.isEnumWithOrdinal()) {
                mapping.setSize(dialect.getDefaultSize(sqlTypeMappingManager.getEnumOrdinalType()));
            }
        }

        //        if (isPk) {
        //            mapping.setNullable(false);
        //            GeneratedValue generatedValue = beanProperty.getAnnotation(GeneratedValue.class);
        //            if (generatedValue != null) {
        //                if (generatedValue.strategy() == GenerationType.IDENTITY
        //                    || generatedValue.strategy() == GenerationType.AUTO) {
        //                    mapping.setAutoincrement(true);
        //                } else {
        //                    // TODO 其他实现，后续慢慢添加-_-
        //                    throw new JdbcMappingException("只实现了IDENTITY, AUTO时使用数据库的自增长的策略");
        //                }
        //            } else {
        //                mapping.setAutoincrement(true);
        //            }
        //        }
    }

    /**
     * Checks if is transient.
     *
     * @param beanProperty the bean property
     * @param logInfo the log info
     * @return true, if is transient
     */
    protected boolean isTransient(BeanProperty<?, ?> beanProperty, StringBuilder logInfo) {
        boolean result = beanProperty.hasAnnotation(java.beans.Transient.class)
            || beanProperty.hasAnnotation(javax.persistence.Transient.class);
        if (result && logger.isDebugEnabled()) {
            logInfo.append(String.format("%s###\t%s is annotated with @Transient, ignore",
                SystemPropertyUtils.getLineSeparator(), beanProperty.getName()));
        }
        return result;
    }

    /**
     * Sets the id generator.
     *
     * @param propertyMapping the property mapping
     * @param bp the bp
     * @param tableName the table name
     * @param columnName the column name
     */
    protected void setIdGenerator(JdbcPropertyMapping propertyMapping, BeanProperty<?, ?> bp, String tableName,
        String columnName) {
        GeneratedValue generatedValue = bp.getAnnotation(GeneratedValue.class);

        IdGenerator idGenerator = null;
        if (generatedValue == null
            || Lang.isEmpty(generatedValue.generator()) && generatedValue.strategy() == GenerationType.AUTO) {
            // 没有指定IdGenerator，默认为AUTO
            // 使用dialect提供的默认IdGenerator
            idGenerator = dialect.getIdGenerator(tableName, columnName);
            if (idGenerator == null) {
                throw new JdbcMappingException(
                    Strings.format("database {} not supported GenerationType.AUTO", dialect.getDatabaseName()));
            }
        } else {
            String generatorName = generatedValue.generator();
            idGenerator = idGeneratorManager.get(generatorName);
            if (idGenerator == null) {
                throw new JdbcMappingException(Strings.format("No IdGenerator named {} was found", generatorName));
            }
        }
        PrimaryKey primaryKey = new PrimaryKey(idGenerator, idGenerator.isDatabaseGeneration());
        propertyMapping.setPrimaryKey(primaryKey);

        if (propertyMapping.isAutoincrement() && dialect instanceof PostgreSQLDialect) {
            propertyMapping.setIgnoreAtInsert(true);
        }
    }

    /**
     * Sets the property mapping.
     *
     * @param mapping the mapping
     * @param beanProperty the bean property
     */
    protected void setPropertyMapping(JdbcPropertyMapping mapping,
        BeanProperty<?, ? extends Serializable> beanProperty) {
        mapping.setPropertyName(beanProperty.getName());
        mapping.setPropertyIndex(beanProperty.getIndex());
        mapping.setPropertyType(beanProperty.getType());
    }

    /**
     * Sets the java sql type mapper.
     *
     * @param <E> the element type
     * @param mapping the mapping
     * @param beanProperty the bean property
     */
    protected <E> void setJavaSqlTypeMapper(JdbcPropertyMapping mapping, Property<?, E> beanProperty) {
        JavaSqlTypeMapper<?> mapper = sqlTypeMappingManager.getJavaSqlTypeMapper(beanProperty);
        if (mapper != null) {
            mapping.setJavaTypeSqlTypeOperator(mapper);
        } else {
            if (beanProperty.getType().isEnum()) {
                @SuppressWarnings({ "rawtypes", "unchecked" })
                Class<Enum> enumType = (Class<Enum>) beanProperty.getType();
                mapping.setJavaTypeSqlTypeOperator(new EnumSqlTypeOperator<>(enumType));
            } else {
                // YUFEI_TODO 后续来优化打开检查，主要是现在父JdbcPropertyMapping（非具体映射）也调用了此方法，造成检查不通过
                //                mapping.setJavaTypeSqlTypeOperator(new DefaultTypesSqlTypeOperator<>(beanProperty.getType(), true));
                JavaTypeSqlTypeOperator<E> operator = BasicOperators.get(beanProperty.getType());
                if (operator != null) {
                    mapping.setJavaTypeSqlTypeOperator(operator);
                } else {
                    mapping.setJavaTypeSqlTypeOperator(new DefaultTypesSqlTypeOperator<>(beanProperty.getType()));
                }
            }
        }
    }

    /**
     * 检查tableMapping是否有问题.
     *
     * @param tableMapping tableMapping
     */
    protected void checkTableMapping(Map<String, JdbcPropertyMapping> tableMapping) {
        Map<String, JdbcPropertyMapping> columns = new HashMap<>();
        for (JdbcPropertyMapping pm : tableMapping.values()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    //                    column.name.mapped = 列{0}已经映射{1},不能再映射{2}
                    if (columns.containsKey(pm.getRepositoryFieldName())) {
                        //                        throw new JdbcMappingException("duplicate mapping column name " + pm.getRepositoryFieldName());
                        JdbcPropertyMapping _pm = columns.get(pm.getRepositoryFieldName());
                        throw new JdbcMappingException("#column.name.mapped",
                            new Object[] { pm.getRepositoryFieldName(), _pm.getPropertyName(), pm.getPropertyName() });
                    }
                    columns.put(pm.getRepositoryFieldName(), pm);
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (pm.isInsertable()) {
                        if (columns.containsKey(subPm.getRepositoryFieldName())) {
                            //                            throw new JdbcMappingException(
                            //                                    "duplicate mapping column name " + pm.getRepositoryFieldName());
                            JdbcPropertyMapping _pm = columns.get(subPm.getRepositoryFieldName());
                            throw new JdbcMappingException("#column.name.mapped", new Object[] {
                                subPm.getRepositoryFieldName(), _pm.getPropertyName(), subPm.getPropertyName() });
                        }
                        columns.put(subPm.getRepositoryFieldName(), subPm);
                    }
                }
            }
        }
    }

    /**
     * Gets the mapping table name.
     *
     * @param type the type
     * @return the mapping table name
     */
    protected String getMappingTableName(Class<?> type) {
        String tableName = null;
        for (ClassNameConversion classNameConversion : classNameConversions) {
            tableName = classNameConversion.getMappingName(type);
            if (Lang.isNotEmpty(tableName)) {
                return tableName;
            }
        }
        return tableName;
    }

    /**
     * Gets the mapping column name.
     *
     * @param type the type
     * @return the mapping column name
     */
    protected String getMappingColumnName(BeanProperty<?, ?> type) {
        String columnName = null;
        for (PropertyNameConversion propertyNameConversion : propertyNameConversions) {
            columnName = propertyNameConversion.getMappingName(type);
            if (Lang.isNotEmpty(columnName)) {
                return columnName;
            }
        }
        return columnName;
    }

    /**
     * Gets the mapping table.
     *
     * @param tableName the table name
     * @return the mapping table
     */
    protected Table getMappingTable(String tableName) {
        Table tm = metadata.getTable(tableName);
        if (checkMapping && tm == null) {
            throw new JdbcMappingException("#table.not.exists", new Object[] { tableName });
        }
        return tm;
    }

    /**
     * Mapping foreign key.
     *
     * @param <E> the element type
     * @param mapping the mapping
     * @param beanProperty the bean property
     * @param columnName the column name
     * @param propertyAccessor the property accessor
     * @param logInfo the log info
     */
    @SuppressWarnings("unchecked")
    protected <E extends Serializable> void mappingForeignKey(JdbcPropertyMapping mapping,
        BeanProperty<?, E> beanProperty, String columnName, PropertyAccessor<Object> propertyAccessor,
        StringBuilder logInfo) {
        mapping.setMode(Mode.MANY_TO_ONE);
        mapping.setRepositoryFieldName(columnName);
        setPropertyMapping(mapping, beanProperty);
        mapping.setProperty(propertyAccessor.getProperty(beanProperty.getIndex()));
        mapping.setSetter(mapping.getProperty()::set);
        mapping.setGetter(mapping.getProperty()::get);
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?, ?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
        if (Lang.isEmpty(bps)) {
            throw new JdbcMappingException("#no.id.property", new Object[] { beanProperty.getType().getName() });
        }
        for (BeanProperty<?, ?> bp : bps) {
            JdbcPropertyMapping columnMapping = new JdbcPropertyMapping();
            columnMapping.setRepositoryFieldName(columnName);
            //            setJavaSqlTypeMapper(columnMapping, bp);
            //            setPropertyMapping(columnMapping, bp);
            columnMapping.setProperty(
                propertyAccessor.getProperty(mapping.getPropertyIndex(), columnMapping.getPropertyIndex()));
            columnMapping.setSetter((obj, value) -> propertyAccessor.setPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }, value));
            columnMapping.setGetter(obj -> (Serializable) propertyAccessor.getPropertyValue(obj,
                mapping.getPropertyIndex(), columnMapping.getPropertyIndex()));
            columnMapping.setPrimaryKey(mapping.getPrimaryKey());
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                    mapping.getPropertyName() + "." + columnMapping.getPropertyName(),
                    columnMapping.getRepositoryFieldName()));
            }
            setColumnMapping(columnMapping, (BeanProperty<?, Serializable>) bp);
            mapping.add(columnMapping);
        }
    }

    /**
     * Creates a new AbstractJdbcMapping object.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the jdbc class mapping
     */
    protected abstract <T> JdbcClassMapping<T> createClassMapping(Class<T> type);

    // ****************************************************************************************************************
    //
    // ****************************************************************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> JdbcClassMapping<T> getClassMapping(Class<T> type) {
        @SuppressWarnings("unchecked")
        JdbcClassMapping<T> classMapping = (JdbcClassMapping<T>) mappedTypes.get(type);
        if (classMapping == null) {
            classMapping = createClassMapping(type);
            mappedTypes.put(type, classMapping);
        }
        return classMapping;
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    @Override
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * 返回sqlTypeMappingManager.
     *
     * @return sqlTypeMappingManager
     */
    @Override
    public SqlTypeMappingManager getSqlTypeMappingManager() {
        return sqlTypeMappingManager;
    }

    /**
     * 返回classNameConversions.
     *
     * @return classNameConversions
     */
    public List<ClassNameConversion> getClassNameConversions() {
        return classNameConversions;
    }

    /**
     * 设置classNameConversions.
     *
     * @param classNameConversions classNameConversions
     */
    public void setClassNameConversions(List<ClassNameConversion> classNameConversions) {
        this.classNameConversions = classNameConversions;
    }

    /**
     * 返回propertyNameConversions.
     *
     * @return propertyNameConversions
     */
    public List<PropertyNameConversion> getPropertyNameConversions() {
        return propertyNameConversions;
    }

    /**
     * 设置propertyNameConversions.
     *
     * @param propertyNameConversions propertyNameConversions
     */
    public void setPropertyNameConversions(List<PropertyNameConversion> propertyNameConversions) {
        this.propertyNameConversions = propertyNameConversions;
    }

    /**
     * 返回metadata.
     *
     * @return metadata
     */
    @Override
    public DatabaseMetadata getMetadata() {
        return metadata;
    }

    /**
     * 返回checkMapping.
     *
     * @return checkMapping
     */
    public boolean isCheckMapping() {
        return checkMapping;
    }

    /**
     * 设置checkMapping.
     *
     * @param checkMapping checkMapping
     */
    public void setCheckMapping(boolean checkMapping) {
        this.checkMapping = checkMapping;
    }
}
