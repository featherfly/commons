
package cn.featherfly.common.db.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.bean.matcher.BeanPropertyNameRegexMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.Table;
import cn.featherfly.common.enums.Logic;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.ClassNameJpaConversion;
import cn.featherfly.common.repository.mapping.ClassNameUnderlineConversion;
import cn.featherfly.common.repository.mapping.MappingException;
import cn.featherfly.common.repository.mapping.MappingFactory;
import cn.featherfly.common.repository.mapping.PropertyMapping;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameJpaConversion;
import cn.featherfly.common.repository.mapping.PropertyNameUnderlineConversion;

/**
 * <p>
 * MapperFactory
 * </p>
 *
 * @author zhongj
 */
public class JdbcMappingFactory implements MappingFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcMappingFactory.class);

    private final Map<Class<?>, ClassMapping<?>> mappedTypes = new HashMap<>();

    private DatabaseMetadata metadata;

    private Dialect dialect;

    private List<ClassNameConversion> classNameConversions = new ArrayList<>();

    private List<PropertyNameConversion> propertyNameConversions = new ArrayList<>();

    /**
     * @param metadata DatabaseMetadata
     * @param dialect  dialect
     */
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        super();
        this.metadata = metadata;
        this.dialect = dialect;
        getClassNameConversions().add(new ClassNameJpaConversion());
        getClassNameConversions().add(new ClassNameUnderlineConversion());

        getPropertyNameConversions().add(new PropertyNameJpaConversion());
        getPropertyNameConversions().add(new PropertyNameUnderlineConversion());
    }

    /**
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        super();
        this.metadata = metadata;
        this.dialect = dialect;

        this.classNameConversions.addAll(classNameConversions);
        this.propertyNameConversions.addAll(propertyNameConversions);
    }

    /**
     * 返回classNameConversions
     *
     * @return classNameConversions
     */
    public List<ClassNameConversion> getClassNameConversions() {
        return classNameConversions;
    }

    /**
     * 设置classNameConversions
     *
     * @param classNameConversions classNameConversions
     */
    public void setClassNameConversions(List<ClassNameConversion> classNameConversions) {
        this.classNameConversions = classNameConversions;
    }

    /**
     * 返回propertyNameConversions
     *
     * @return propertyNameConversions
     */
    public List<PropertyNameConversion> getPropertyNameConversions() {
        return propertyNameConversions;
    }

    /**
     * 设置propertyNameConversions
     *
     * @param propertyNameConversions propertyNameConversions
     */
    public void setPropertyNameConversions(List<PropertyNameConversion> propertyNameConversions) {
        this.propertyNameConversions = propertyNameConversions;
    }

    /**
     * 返回metadata
     *
     * @return metadata
     */
    public DatabaseMetadata getMetadata() {
        return metadata;
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

    private <T> ClassMapping<T> createClassMapping(Class<T> type) {
        Map<String, PropertyMapping> tableMapping = new HashMap<>();

        StringBuilder logInfo = new StringBuilder();
        // // 从对象中读取有Column的列，找到显示映射，使用scan扫描
        BeanDescriptor<T> bd = BeanDescriptor.getBeanDescriptor(type);
        String tableName = getMappingTableName(type);
        tableName = dialect.convertTableOrColumnName(tableName);

        if (LOGGER.isDebugEnabled()) {
            logInfo.append(
                    String.format("###%s类%s映射到表%s", SystemPropertyUtils.getLineSeparator(), type.getName(), tableName));
        }

        Table tm = metadata.getTable(tableName);
        if (tm == null) {
            throw new MappingException("#talbe.not.exists", new Object[] { tableName });
        }

        Collection<BeanProperty<?>> bps = bd
                .findBeanPropertys(new BeanPropertyAnnotationMatcher(Logic.OR, Column.class, Id.class, Embedded.class));
        boolean findPk = false;
        for (BeanProperty<?> beanProperty : bps) {
            if (mappingWithJpa(beanProperty, tableMapping, logInfo, tm)) {
                findPk = true;
            }
        }
        if (!findPk) {
            throw new MappingException("#id.map.not.exists", new Object[] { type.getName() });
        }

        for (cn.featherfly.common.db.metadata.Column cmd : tm.getColumns()) {
            mappingFromColumnMetadata(bd, tableMapping, cmd, logInfo);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(logInfo.toString());
        }
        // 形成映射对象
        ClassMapping<T> classMapping = new ClassMapping<>(type, tableName);
        classMapping.addPropertyMappings(tableMapping.values());
        return classMapping;
    }

    private boolean mappingWithJpa(BeanProperty<?> beanProperty, Map<String, PropertyMapping> tableMapping,
            StringBuilder logInfo, Table tableMetadata) {
        boolean hasPk = beanProperty.hasAnnotation(Id.class);
        PropertyMapping mapping = new PropertyMapping();

        Embedded embedded = beanProperty.getAnnotation(Embedded.class);
        if (embedded != null) {
            mappinEmbedded(mapping, beanProperty, logInfo, tableMetadata);
            tableMapping.put(mapping.getRepositoryFieldName(), mapping);
        } else {
            String columnName = getMappingColumnName(beanProperty);
            if (LangUtils.isNotEmpty(columnName)) {
                columnName = dialect.convertTableOrColumnName(columnName);
                ManyToOne manyToOne = beanProperty.getAnnotation(ManyToOne.class);
                OneToOne oneToOne = beanProperty.getAnnotation(OneToOne.class);
                mapping.setPropertyName(beanProperty.getName());
                mapping.setPropertyType(beanProperty.getType());
                mapping.setPrimaryKey(hasPk);
                if (manyToOne != null || oneToOne != null) {
                    mapping.setRepositoryFieldName(columnName);
                    mappingFk(mapping, beanProperty, columnName, hasPk, logInfo);
                } else {
                    mapping.setRepositoryFieldName(columnName);
                }
                tableMapping.put(mapping.getRepositoryFieldName(), mapping);
                if (LOGGER.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                            mapping.getPropertyName(), mapping.getRepositoryFieldName()));
                }
            }
        }
        return hasPk;
    }

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

            if (bp.getAnnotation(Column.class) != null) {
                if (LOGGER.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                            mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                            columnMpping.getRepositoryFieldName()));
                }
                mapping.add(columnMpping);
            } else {
                cn.featherfly.common.db.metadata.Column columnMetadata = tableMetadata.getColumn(columnName);
                if (columnMetadata != null) {
                    mapping.add(columnMpping);
                    if (LOGGER.isDebugEnabled()) {
                        logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                                columnMpping.getPropertyName(), columnMpping.getRepositoryFieldName()));
                    }
                } else if (LOGGER.isDebugEnabled()) {
                    logInfo.append(String.format("%s\t没有属性 -> %s [列%s的隐式映射]", SystemPropertyUtils.getLineSeparator(),
                            mapping.getPropertyName() + "." + bp.getName(), columnName));
                }
            }
        }
    }

    private void mappingFk(PropertyMapping mapping, BeanProperty<?> beanProperty, String columnName, boolean hasPk,
            StringBuilder logInfo) {
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
        if (LangUtils.isEmpty(bps)) {
            throw new MappingException(beanProperty.getType().getName() + " no property annotated with @Id");
        }
        for (BeanProperty<?> bp : bps) {
            PropertyMapping columnMpping = new PropertyMapping();
            columnMpping.setRepositoryFieldName(columnName);
            columnMpping.setPropertyType(bp.getType());
            columnMpping.setPropertyName(bp.getName());
            // columnMpping.setPropertyPath(dialect.wrapName(beanProperty.getName()
            // + "." + bp.getName()));
            columnMpping.setPrimaryKey(hasPk);
            if (LOGGER.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                        columnMpping.getRepositoryFieldName()));
            }
            mapping.add(columnMpping);
        }
    }

    private <T> void mappingFromColumnMetadata(BeanDescriptor<T> bd, Map<String, PropertyMapping> tableMapping,
            cn.featherfly.common.db.metadata.Column cmd, StringBuilder logInfo) {
        Map<String, PropertyMapping> nameSet = new HashMap<>();
        tableMapping.forEach((k, v) -> {
            if (LangUtils.isNotEmpty(k)) {
                nameSet.put(k, v);
            } else {
                if (LangUtils.isNotEmpty(v.getPropertyMappings())) {
                    v.getPropertyMappings().forEach(pm -> {
                        nameSet.put(pm.getRepositoryFieldName(), pm);
                    });
                }
            }
        });
        if (!nameSet.containsKey(cmd.getName())) {
            // 转换下划线，并使用驼峰
            // String columnName = cmd.getName().toLowerCase();
            String columnName = cmd.getName().toLowerCase();
            String propertyName = WordUtils.parseToUpperFirst(columnName, Chars.UNDER_LINE.charAt(0));
            BeanProperty<?> beanProperty = bd.findBeanProperty(new BeanPropertyNameRegexMatcher(propertyName));
            if (beanProperty != null) {
                PropertyMapping mapping = new PropertyMapping();
                mapping.setPropertyType(beanProperty.getType());
                mapping.setRepositoryFieldName(dialect.convertTableOrColumnName(columnName));
                mapping.setPropertyName(propertyName);
                mapping.setPrimaryKey(cmd.isPrimaryKey());
                mapping.setDefaultValue(cmd.getDefaultValue());
                tableMapping.put(mapping.getRepositoryFieldName(), mapping);
                if (LOGGER.isDebugEnabled()) {
                    logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                            mapping.getPropertyName(), mapping.getRepositoryFieldName()));
                }
            } else {
                if (LOGGER.isDebugEnabled()) {
                    logInfo.append(String.format("%s\t没有属性 -> %s [列%s的隐式映射]", SystemPropertyUtils.getLineSeparator(),
                            propertyName, cmd.getName()));
                }
            }
        } else {
            PropertyMapping mapping = nameSet.get(cmd.getName());
            mapping.setPrimaryKey(cmd.isPrimaryKey());
            mapping.setDefaultValue(cmd.getDefaultValue());
        }
    }

    private String getMappingTableName(Class<?> type) {
        String tableName = null;
        for (ClassNameConversion classNameConversion : classNameConversions) {
            tableName = classNameConversion.getMappingName(type);
            if (LangUtils.isNotEmpty(tableName)) {
                return tableName;
            }
        }
        return tableName;
    }

    private String getMappingColumnName(BeanProperty<?> type) {
        String columnName = null;
        for (PropertyNameConversion propertyNameConversion : propertyNameConversions) {
            columnName = propertyNameConversion.getMappingName(type);
            if (LangUtils.isNotEmpty(columnName)) {
                return columnName;
            }
        }
        return columnName;
    }

    /**
     * 返回dialect
     *
     * @return dialect
     */
    public Dialect getDialect() {
        return dialect;
    }
}
