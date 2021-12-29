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
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.bean.matcher.BeanPropertyNameRegexMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.enums.Logic;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PropertyMapping;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * <p>
 * MapperFactory
 * </p>
 * .
 *
 * @author zhongj
 */
/**
 * <p>
 * ObjectToDbMixedMappingFactory
 * </p>
 *
 * @author zhongj
 */
public class ObjectDbMixedMappingFactory extends AbstractJdbcMappingFactory {

    /**
     * Instantiates a new object db mixed mapping factory.
     *
     * @param metadata DatabaseMetadata
     * @param dialect  dialect
     */
    public ObjectDbMixedMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        super(metadata, dialect);
    }

    /**
     * Instantiates a new object db mixed mapping factory.
     *
     * @param metadata              DatabaseMetadata
     * @param dialect               dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public ObjectDbMixedMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        super(metadata, dialect, sqlTypeMappingManager);
    }

    /**
     * Instantiates a new object db mixed mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public ObjectDbMixedMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        super(metadata, dialect, classNameConversions, propertyNameConversions);
    }

    /**
     * Instantiates a new object db mixed mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public ObjectDbMixedMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        super(metadata, dialect, sqlTypeMappingManager, classNameConversions, propertyNameConversions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> ClassMapping<T> getClassMapping(Class<T> type) {
        @SuppressWarnings("unchecked")
        ClassMapping<T> classMapping = (ClassMapping<T>) mappedTypes.get(type);
        if (classMapping == null) {
            classMapping = createClassMapping(type);
            mappedTypes.put(type, classMapping);
        }
        return classMapping;
    }

    /**
     * Creates a new ObjectDbMixedMapping object.
     *
     * @param <T>  the generic type
     * @param type the type
     * @return the class mapping< t>
     */
    private <T> ClassMapping<T> createClassMapping(Class<T> type) {
        Map<String, PropertyMapping> tableMapping = new HashMap<>();

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
            throw new JdbcMappingException("#talbe.not.exists", new Object[] { tableName });
        }

        Collection<BeanProperty<?>> bps = bd
                .findBeanPropertys(new BeanPropertyAnnotationMatcher(Logic.OR, Id.class, Column.class, Embedded.class));

        boolean findPk = false;
        for (BeanProperty<?> beanProperty : bps) {
            if (mappingWithJpa(beanProperty, tableMapping, logInfo, tm)) {
                findPk = true;
            }
        }
        if (!findPk) {
            throw new JdbcMappingException("#id.map.not.exists", new Object[] { type.getName() });
        }

        for (cn.featherfly.common.db.Column cmd : tm.getColumns()) {
            mappingFromColumnMetadata(bd, tableMapping, cmd, logInfo);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(logInfo.toString());
        }
        // 形成映射对象
        ClassMapping<T> classMapping = new ClassMapping<>(type, tableName, tm.getRemark());

        classMapping.addPropertyMappings(tableMapping.values().stream()
                .sorted((p1, p2) -> p1.getIndex() < p2.getIndex() ? -1 : 1).collect(Collectors.toList()));
        return classMapping;
    }

    /**
     * Mapping with jpa.
     *
     * @param beanProperty  the bean property
     * @param tableMapping  the table mapping
     * @param logInfo       the log info
     * @param tableMetadata the table metadata
     * @return true, if successful
     */
    private boolean mappingWithJpa(BeanProperty<?> beanProperty, Map<String, PropertyMapping> tableMapping,
            StringBuilder logInfo, Table tableMetadata) {
        if (isTransient(beanProperty, logInfo)) {
            return false;
        }
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        PropertyMapping mapping = new PropertyMapping();

        Embedded embedded = beanProperty.getAnnotation(Embedded.class);
        if (embedded != null) {
            mappinEmbedded(mapping, beanProperty, logInfo, tableMetadata);
            tableMapping.put(mapping.getRepositoryFieldName(), mapping);
        } else {
            String columnName = getMappingColumnName(beanProperty);
            if (Lang.isNotEmpty(columnName)) {
                columnName = dialect.convertTableOrColumnName(columnName);
                mapping.setPropertyName(beanProperty.getName());
                mapping.setPropertyType(beanProperty.getType());
                mapping.setPrimaryKey(isPk);
                ManyToOne manyToOne = beanProperty.getAnnotation(ManyToOne.class);
                OneToOne oneToOne = beanProperty.getAnnotation(OneToOne.class);
                if (manyToOne != null || oneToOne != null) {
                    mapping.setRepositoryFieldName(columnName);
                    mappingFk(mapping, beanProperty, columnName, isPk, logInfo);
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
     * @param mapping       the mapping
     * @param beanProperty  the bean property
     * @param logInfo       the log info
     * @param tableMetadata the table metadata
     */
    private void mappinEmbedded(PropertyMapping mapping, BeanProperty<?> beanProperty, StringBuilder logInfo,
            Table tableMetadata) {
        mapping.setPropertyName(beanProperty.getName());
        mapping.setPropertyType(beanProperty.getType());
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        // Collection<BeanProperty<?>> bps = bd.findBeanPropertys(new
        // BeanPropertyAnnotationMatcher(Column.class));
        Collection<BeanProperty<?>> bps = bd.getBeanProperties();
        for (BeanProperty<?> bp : bps) {
            String columnName = getMappingColumnName(bp);
            columnName = dialect.convertTableOrColumnName(columnName);
            PropertyMapping columnMpping = new PropertyMapping();
            columnMpping.setRepositoryFieldName(columnName);
            columnMpping.setPropertyType(bp.getType());
            columnMpping.setPropertyName(bp.getName());

            Column column = bp.getAnnotation(Column.class);
            if (column != null) {
                if (logger.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                            mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                            columnMpping.getRepositoryFieldName()));
                }
                setColumnMapping(mapping, beanProperty);

                mapping.add(columnMpping);
            } else {
                cn.featherfly.common.db.Column columnMetadata = tableMetadata.getColumn(columnName);
                if (columnMetadata != null) {
                    mapping.add(columnMpping);
                    if (logger.isDebugEnabled()) {
                        logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                                columnMpping.getPropertyName(), columnMpping.getRepositoryFieldName()));
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
     * @param mapping      the mapping
     * @param beanProperty the bean property
     * @param columnName   the column name
     * @param hasPk        the has pk
     * @param logInfo      the log info
     */
    private void mappingFk(PropertyMapping mapping, BeanProperty<?> beanProperty, String columnName, boolean hasPk,
            StringBuilder logInfo) {
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
        if (Lang.isEmpty(bps)) {
            throw new JdbcMappingException("#no.id.property", new Object[] { beanProperty.getType().getName() });
        }
        for (BeanProperty<?> bp : bps) {
            if (isTransient(bp, logInfo)) {
                continue;
            }
            PropertyMapping columnMpping = new PropertyMapping();
            columnMpping.setRepositoryFieldName(columnName);
            columnMpping.setPropertyType(bp.getType());
            columnMpping.setPropertyName(bp.getName());
            // columnMpping.setPropertyPath(dialect.wrapName(beanProperty.getName()
            // + "." + bp.getName()));
            columnMpping.setPrimaryKey(hasPk);
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                        columnMpping.getRepositoryFieldName()));
            }
            mapping.add(columnMpping);
        }
    }

    /**
     * Mapping from column metadata.
     *
     * @param <T>          the generic type
     * @param bd           the bd
     * @param tableMapping the table mapping
     * @param cmd          the cmd
     * @param logInfo      the log info
     */
    private <T> void mappingFromColumnMetadata(BeanDescriptor<T> bd, Map<String, PropertyMapping> tableMapping,
            cn.featherfly.common.db.Column cmd, StringBuilder logInfo) {
        Map<String, PropertyMapping> nameSet = new HashMap<>();
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
            BeanProperty<?> beanProperty = bd.findBeanProperty(new BeanPropertyNameRegexMatcher(propertyName));
            if (beanProperty != null && !isTransient(beanProperty, logInfo)) {
                PropertyMapping mapping = new PropertyMapping();
                mapping.setPropertyType(beanProperty.getType());
                mapping.setPropertyName(propertyName);

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
            PropertyMapping mapping = nameSet.get(columnNameLowerCase);
            setMappingColumnValue(mapping, cmd, false);
            if (mapping.getPropertyMappings().size() == 1) {
                setMappingColumnValue(mapping.getPropertyMappings().get(0), cmd, false);
            }
        }
    }

    private void setMappingColumnValue(PropertyMapping mapping, cn.featherfly.common.db.Column cmd,
            boolean convertColumnName) {
        mapping.setRemark(cmd.getRemark());
        mapping.setNullable(cmd.isNullable());
        mapping.setDecimalDigits(cmd.getDecimalDigits());
        mapping.setAutoincrement(cmd.isAutoincrement());
        mapping.setSize(cmd.getSize());
        mapping.setPrimaryKey(cmd.isPrimaryKey());
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
    private String getMappingColumnName(BeanProperty<?> type) {
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
