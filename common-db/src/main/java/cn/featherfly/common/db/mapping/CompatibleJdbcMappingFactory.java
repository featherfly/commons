package cn.featherfly.common.db.mapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.bean.PropertyAccessorFactory;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.bean.matcher.BeanPropertyNameRegexMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.operator.LogicOperator;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PrimaryKey;
import cn.featherfly.common.repository.mapping.PropertyMapping.Mode;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * CompatibleJdbcMappingFactory.
 *
 * @author zhongj
 */
public class CompatibleJdbcMappingFactory extends AbstractJdbcMappingFactory {

    /**
     * Instantiates a new compatible jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param classNameConversions the class name conversions
     * @param propertyNameConversions the property name conversions
     * @param propertyAccessorFactory the property accessor factory
     */
    public CompatibleJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions,
        PropertyAccessorFactory propertyAccessorFactory) {
        super(metadata, dialect, classNameConversions, propertyNameConversions, propertyAccessorFactory);
    }

    /**
     * Instantiates a new compatible jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param propertyAccessorFactory the property accessor factory
     */
    public CompatibleJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        PropertyAccessorFactory propertyAccessorFactory) {
        super(metadata, dialect, propertyAccessorFactory);
    }

    /**
     * Instantiates a new compatible jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param classNameConversions the class name conversions
     * @param propertyNameConversions the property name conversions
     * @param propertyAccessorFactory the property accessor factory
     */
    public CompatibleJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
        List<PropertyNameConversion> propertyNameConversions, PropertyAccessorFactory propertyAccessorFactory) {
        super(metadata, dialect, sqlTypeMappingManager, classNameConversions, propertyNameConversions,
            propertyAccessorFactory);
    }

    /**
     * Instantiates a new compatible jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param propertyAccessorFactory the property accessor factory
     */
    public CompatibleJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, PropertyAccessorFactory propertyAccessorFactory) {
        super(metadata, dialect, sqlTypeMappingManager, propertyAccessorFactory);
    }

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
     * Creates a new ObjectDbMixedMapping object.
     *
     * @param <T> the generic type
     * @param type the type
     * @return the class mapping< t>
     */
    @SuppressWarnings("unchecked")
    private <T> JdbcClassMapping<T> createClassMapping(Class<T> type) {
        Map<String, JdbcPropertyMapping> tableMapping = new HashMap<>();

        StringBuilder logInfo = new StringBuilder();
        // // 从对象中读取有Column的列，找到显示映射，使用scan扫描
        BeanDescriptor<T> bd = BeanDescriptor.getBeanDescriptor(type);
        String tableName = getMappingTableName(type);
        tableName = dialect.convertTableOrColumnName(tableName);

        if (logger.isDebugEnabled()) {
            logInfo.append(
                String.format("###%s类%s映射到表%s", SystemPropertyUtils.getLineSeparator(), type.getName(), tableName));
        }

        Table tm = metadata.getTable(tableName);
        if (tm == null) {
            throw new JdbcMappingException("#table.not.exists", new Object[] { tableName });
        }

        // YUFEI_TEST 需要测试看classLoader是否正常
        PropertyAccessor<
            T> propertyAccessor = propertyAccessorFactory.create(type, Thread.currentThread().getContextClassLoader());

        Collection<BeanProperty<?, ?>> bps = bd.findBeanPropertys(
            new BeanPropertyAnnotationMatcher(LogicOperator.OR, Id.class, Column.class, Embedded.class));

        boolean findPk = false;
        for (BeanProperty<?, ?> beanProperty : bps) {
            if (mappingWithJpa((BeanProperty<Object, ?>) beanProperty, tableMapping, logInfo, tm,
                (PropertyAccessor<Object>) propertyAccessor)) {
                findPk = true;
            }
        }
        if (!findPk) {
            throw new JdbcMappingException("#id.map.not.exists", new Object[] { type.getName() });
        }

        for (cn.featherfly.common.db.Column cmd : tm.getColumns()) {
            mappingFromColumnMetadata(bd, tableMapping, cmd, logInfo, (PropertyAccessor<Object>) propertyAccessor);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(logInfo.toString());
        }

        // 检查tableMapping
        checkTableMapping(tableMapping);

        // 形成映射对象
        JdbcClassMapping<T> classMapping = new JdbcClassMapping<>(type, tableName, tm.getRemark());

        classMapping.addPropertyMappings(tableMapping.values().stream()
            .sorted((p1, p2) -> p1.getIndex() < p2.getIndex() ? -1 : 1).collect(Collectors.toList()));

        logger.debug("class mapping {}", classMapping);
        return classMapping;
    }

    /**
     * Mapping with jpa.
     *
     * @param beanProperty the bean property
     * @param tableMapping the table mapping
     * @param mappedColumns the mapped columns
     * @param logInfo the log info
     * @param tableMetadata the table metadata
     * @return true, if successful
     */
    private boolean mappingWithJpa(BeanProperty<Object, ?> beanProperty, Map<String, JdbcPropertyMapping> tableMapping,
        StringBuilder logInfo, Table tableMetadata, PropertyAccessor<Object> propertyAccessor) {
        if (isTransient(beanProperty, logInfo)) {
            return false;
        }
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        JdbcPropertyMapping mapping = new JdbcPropertyMapping();
        setJavaSqlTypeMapper(mapping, beanProperty);

        Embedded embedded = beanProperty.getAnnotation(Embedded.class);
        if (embedded != null) {
            mappinEmbedded(mapping, beanProperty, logInfo, tableMetadata, propertyAccessor);
            tableMapping.put(mapping.getRepositoryFieldName(), mapping);
        } else {
            String columnName = getMappingColumnName(beanProperty);
            if (Lang.isNotEmpty(columnName)) {
                columnName = dialect.convertTableOrColumnName(columnName);
                mapping.setPropertyName(beanProperty.getName());
                mapping.setPropertyIndex(beanProperty.getIndex());
                mapping.setPropertyType(beanProperty.getType());
                mapping.setSetter(propertyAccessor.getProperty(beanProperty.getIndex())::set);
                mapping.setGetter(propertyAccessor.getProperty(beanProperty.getIndex())::get);
                if (isPk) {
                    setIdGenerator(mapping, beanProperty, tableMetadata.getName(), columnName);
                }
                ManyToOne manyToOne = beanProperty.getAnnotation(ManyToOne.class);
                OneToOne oneToOne = beanProperty.getAnnotation(OneToOne.class);
                if (manyToOne != null || oneToOne != null) {
                    mapping.setRepositoryFieldName(columnName);
                    mappingFk(mapping, beanProperty, columnName, propertyAccessor, logInfo);
                } else {
                    mapping.setRepositoryFieldName(columnName);
                    setColumnMapping(mapping, beanProperty);
                }
                tableMapping.put(mapping.getRepositoryFieldName(), mapping);
                if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName(), mapping.getRepositoryFieldName()));
                }
            }
        }
        return isPk;
    }

    /**
     * Mappin embedded.
     *
     * @param mapping the mapping
     * @param beanProperty the bean property
     * @param logInfo the log info
     * @param tableMetadata the table metadata
     */
    private void mappinEmbedded(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty, StringBuilder logInfo,
        Table tableMetadata, PropertyAccessor<Object> propertyAccessor) {
        mapping.setMode(Mode.EMBEDDED);
        mapping.setPropertyName(beanProperty.getName());
        mapping.setPropertyIndex(beanProperty.getIndex());
        mapping.setPropertyType(beanProperty.getType());
        mapping.setSetter(propertyAccessor.getProperty(beanProperty.getIndex())::set);
        mapping.setGetter(propertyAccessor.getProperty(beanProperty.getIndex())::get);
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        for (BeanProperty<?, ?> bp : bd.getBeanProperties()) {
            String columnName = getMappingColumnName(bp);
            columnName = dialect.convertTableOrColumnName(columnName);
            JdbcPropertyMapping columnMapping = new JdbcPropertyMapping();
            setJavaSqlTypeMapper(columnMapping, bp);
            columnMapping.setRepositoryFieldName(columnName);
            columnMapping.setPropertyType(bp.getType());
            columnMapping.setPropertyName(bp.getName());
            columnMapping.setPropertyIndex(bp.getIndex());
            columnMapping.setSetter((obj, value) -> propertyAccessor.setPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }, value));
            columnMapping.setGetter(obj -> propertyAccessor.getPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }));

            Column column = bp.getAnnotation(Column.class);
            if (column != null) {
                if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + columnMapping.getPropertyName(),
                        columnMapping.getRepositoryFieldName()));
                }
                setColumnMapping(mapping, beanProperty);

                mapping.add(columnMapping);
            } else {
                cn.featherfly.common.db.Column columnMetadata = tableMetadata.getColumn(columnName);
                if (columnMetadata != null) {
                    mapping.add(columnMapping);
                    if (logger.isDebugEnabled()) {
                        logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                            columnMapping.getPropertyName(), columnMapping.getRepositoryFieldName()));
                    }
                } else if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s\t没有属性 -> %s [列%s的隐式映射]", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + bp.getName(), columnName));
                }
            }
        }
    }

    /**
     * Mapping fk.
     *
     * @param mapping the mapping
     * @param beanProperty the bean property
     * @param columnName the column name
     * @param hasPk the has pk
     * @param logInfo the log info
     */
    private void mappingFk(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty, String columnName,
        PropertyAccessor<Object> propertyAccessor, StringBuilder logInfo) {
        mapping.setMode(Mode.MANY_TO_ONE);
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?, ?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
        if (Lang.isEmpty(bps)) {
            throw new JdbcMappingException("#no.id.property", new Object[] { beanProperty.getType().getName() });
        }
        for (BeanProperty<?, ?> bp : bps) {
            if (isTransient(bp, logInfo)) {
                continue;
            }
            JdbcPropertyMapping columnMapping = new JdbcPropertyMapping();
            setJavaSqlTypeMapper(columnMapping, bp);
            columnMapping.setRepositoryFieldName(columnName);
            columnMapping.setPropertyType(bp.getType());
            columnMapping.setPropertyName(bp.getName());
            columnMapping.setPropertyIndex(bp.getIndex());
            columnMapping.setSetter((obj, value) -> propertyAccessor.setPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }, value));
            columnMapping.setGetter(obj -> propertyAccessor.getPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }));
            columnMapping.setPrimaryKey(mapping.getPrimaryKey());
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                    mapping.getPropertyName() + "." + columnMapping.getPropertyName(),
                    columnMapping.getRepositoryFieldName()));
            }
            mapping.add(columnMapping);
        }
    }

    /**
     * Mapping from column metadata.
     *
     * @param <T> the generic type
     * @param bd the bd
     * @param tableMapping the table mapping
     * @param cmd the cmd
     * @param logInfo the log info
     */
    private <T> void mappingFromColumnMetadata(BeanDescriptor<T> bd, Map<String, JdbcPropertyMapping> tableMapping,
        cn.featherfly.common.db.Column cmd, StringBuilder logInfo, PropertyAccessor<Object> propertyAccessor) {
        Map<String, JdbcPropertyMapping> nameSet = new HashMap<>();
        tableMapping.forEach((column, propertyMapping) -> {
            if (Lang.isNotEmpty(column)) {
                // 名称转换为小写
                nameSet.put(column.toLowerCase(), propertyMapping);
            } else {
                if (Lang.isNotEmpty(propertyMapping.getPropertyMappings())) {
                    propertyMapping.getPropertyMappings().forEach(pm -> {
                        nameSet.put(pm.getRepositoryFieldName().toLowerCase(), pm);
                    });
                }
            }
        });
        // 名称转换为小写
        String columnNameLowerCase = cmd.getName().toLowerCase();
        if (!nameSet.containsKey(columnNameLowerCase)) {
            // 转换下划线，并使用驼峰
            String propertyName = WordUtils.parseToUpperFirst(columnNameLowerCase, Chars.UNDER_LINE_CHAR);
            BeanProperty<?, ?> beanProperty = bd.findBeanProperty(new BeanPropertyNameRegexMatcher(propertyName));
            if (beanProperty != null && !isTransient(beanProperty, logInfo)) {
                JdbcPropertyMapping mapping = new JdbcPropertyMapping();
                setJavaSqlTypeMapper(mapping, beanProperty);
                mapping.setPropertyType(beanProperty.getType());
                mapping.setPropertyName(beanProperty.getName());
                mapping.setPropertyIndex(beanProperty.getIndex());
                mapping.setSetter(propertyAccessor.getProperty(beanProperty.getIndex())::set);
                mapping.setGetter(propertyAccessor.getProperty(beanProperty.getIndex())::get);

                setMappingColumnValue(mapping, cmd, false);

                tableMapping.put(mapping.getRepositoryFieldName(), mapping);
                if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName(), mapping.getRepositoryFieldName()));
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s\t没有属性 -> %s [列%s的隐式映射]", SystemPropertyUtils.getLineSeparator(),
                        propertyName, cmd.getName()));
                }
            }
        } else {
            JdbcPropertyMapping mapping = nameSet.get(columnNameLowerCase);
            setMappingColumnValue(mapping, cmd, false);
            if (mapping.getPropertyMappings().size() == 1) {
                setMappingColumnValue(mapping.getPropertyMappings().get(0), cmd, false);
            }
        }
    }

    private void setMappingColumnValue(JdbcPropertyMapping mapping, cn.featherfly.common.db.Column cmd,
        boolean convertColumnName) {
        mapping.setRemark(cmd.getRemark());
        mapping.setNullable(cmd.isNullable());
        mapping.setDecimalDigits(cmd.getDecimalDigits());
        mapping.setAutoincrement(cmd.isAutoincrement());
        mapping.setSize(cmd.getSize());
        if (cmd.isPrimaryKey()) {
            PrimaryKey primaryKey = new PrimaryKey(dialect.getIdGenerator(cmd.getTable().getName(), cmd.getName()));
            mapping.setPrimaryKey(primaryKey);
        }
        mapping.setDefaultValue(cmd.getDefaultValue());
        mapping.setIndex(cmd.getColumnIndex());
        if (convertColumnName) {
            mapping.setRepositoryFieldName(dialect.convertTableOrColumnName(cmd.getName()));
        } else {
            mapping.setRepositoryFieldName(cmd.getName());
        }

    }

    /**
     * Gets the mapping table name.
     *
     * @param type the type
     * @return the mapping table name
     */
    private String getMappingTableName(Class<?> type) {
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
    private String getMappingColumnName(BeanProperty<?, ?> type) {
        String columnName = null;
        for (PropertyNameConversion propertyNameConversion : propertyNameConversions) {
            columnName = propertyNameConversion.getMappingName(type);
            if (Lang.isNotEmpty(columnName)) {
                return columnName;
            }
        }
        return columnName;
    }
}
