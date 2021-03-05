
package cn.featherfly.common.db.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.matcher.BeanPropertyAnnotationMatcher;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.jpa.Comment;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.repository.Index;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PropertyMapping;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * <p>
 * ObjectMappingFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public class ObjectToDbMappingFactory extends AbstractJdbcMappingFactory {

    private boolean checkMapping = true;

    /**
     * @param metadata the metadata
     * @param dialect  the dialect
     */
    public ObjectToDbMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        super(metadata, dialect);
    }

    /**
     * @param metadata              the metadata
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public ObjectToDbMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        super(metadata, dialect, sqlTypeMappingManager);
    }

    /**
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public ObjectToDbMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        super(metadata, dialect, classNameConversions, propertyNameConversions);
    }

    /**
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public ObjectToDbMappingFactory(DatabaseMetadata metadata, Dialect dialect,
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

    private <T> List<Index> createIndexs(javax.persistence.Table table) {
        List<Index> indexs = new ArrayList<>();
        if (table != null) {
            for (javax.persistence.Index index : table.indexes()) {
                indexs.add(new Index(index.name(), index.columnList().split(Chars.COMMA), index.unique()));
            }
            for (UniqueConstraint unique : table.uniqueConstraints()) {
                indexs.add(new Index(unique.name(), unique.columnNames(), true));
            }
        }
        return indexs;
    }

    private <T> ClassMapping<T> createClassMapping(Class<T> type) {
        Map<String, PropertyMapping> tableMapping = new LinkedHashMap<>();
        StringBuilder logInfo = new StringBuilder();
        // 从对象中读取有Column的列，找到显示映射，使用scan扫描
        BeanDescriptor<T> bd = BeanDescriptor.getBeanDescriptor(type);
        String tableName = getMappingTableName(type);
        tableName = dialect.convertTableOrColumnName(tableName);
        String remark = null;
        String schema = null;
        Comment comment = bd.getAnnotation(Comment.class);
        if (comment != null) {
            remark = comment.value();
        }
        javax.persistence.Table table = bd.getAnnotation(javax.persistence.Table.class);
        if (table != null) {
            schema = table.schema();
        }
        // object to db 严格按照对象映射
        //        if (Lang.isEmpty(schema)) {
        //            schema = dialect.getDefaultSchema(metadata.getName());
        //        }
        if (logger.isDebugEnabled()) {
            logInfo.append(
                    String.format("###%s类%s映射到表%s", SystemPropertyUtils.getLineSeparator(), type.getName(), tableName));
        }

        Collection<BeanProperty<?>> bps = bd.getBeanProperties();
        boolean findPk = false;
        int pkNo = 0;
        for (BeanProperty<?> beanProperty : bps) {
            if (mappingWithJpa(beanProperty, tableMapping, logInfo)) {
                findPk = true;
                pkNo++;
            }
        }
        if (!findPk) {
            throw new JdbcMappingException("#id.map.not.exists", new Object[] { type.getName() });
        }
        if (checkMapping) {
            Table tm = getMappingTable(tableName);
            checkMapping(bd, tableMapping, tm);
        }
        if (logger.isDebugEnabled()) {
            logger.debug(logInfo.toString());
        }
        // 形成映射对象
        ClassMapping<T> classMapping = new ClassMapping<>(type, tableName, schema, remark);
        classMapping.addIndexs(createIndexs(table));

        classMapping.addPropertyMappings(tableMapping.values().stream()
                .sorted((p1, p2) -> p1.getIndex() < p2.getIndex() ? -1 : 1).collect(Collectors.toList()));
        if (pkNo > 1) {
            classMapping.getPropertyMappings().forEach(pm -> {
                if (pm.isPrimaryKey()) {
                    pm.setAutoincrement(false);
                }
            });
        }
        return classMapping;
    }

    private boolean mappingWithJpa(BeanProperty<?> beanProperty, Map<String, PropertyMapping> tableMapping,
            StringBuilder logInfo) {
        if (isTransient(beanProperty, logInfo)) {
            return false;
        }
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        PropertyMapping mapping = new PropertyMapping();

        Embedded embedded = beanProperty.getAnnotation(Embedded.class);
        if (embedded != null) {
            mappinEmbedded(mapping, beanProperty, logInfo);
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
                    mapping.setNullable(!isPk);
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

    private void mappinEmbedded(PropertyMapping mapping, BeanProperty<?> beanProperty, StringBuilder logInfo) {
        mapping.setPropertyName(beanProperty.getName());
        mapping.setPropertyType(beanProperty.getType());
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?>> bps = bd.getBeanProperties();
        for (BeanProperty<?> bp : bps) {
            if (isTransient(bp, logInfo)) {
                continue;
            }
            String columnName = getMappingColumnName(bp);
            columnName = dialect.convertTableOrColumnName(columnName);
            PropertyMapping columnMpping = new PropertyMapping();
            columnMpping.setRepositoryFieldName(columnName);
            columnMpping.setPropertyType(bp.getType());
            columnMpping.setPropertyName(bp.getName());
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                        columnMpping.getRepositoryFieldName()));
            }
            setColumnMapping(columnMpping, bp);
            mapping.add(columnMpping);
        }
    }

    private void mappingFk(PropertyMapping mapping, BeanProperty<?> beanProperty, String columnName, boolean hasPk,
            StringBuilder logInfo) {
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        Collection<BeanProperty<?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
        if (Lang.isEmpty(bps)) {
            throw new JdbcMappingException("#no.id.property", new Object[] { beanProperty.getType().getName() });
        }
        for (BeanProperty<?> bp : bps) {
            PropertyMapping columnMpping = new PropertyMapping();
            columnMpping.setRepositoryFieldName(columnName);
            columnMpping.setPropertyType(bp.getType());
            columnMpping.setPropertyName(bp.getName());
            columnMpping.setPrimaryKey(hasPk);
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                        mapping.getPropertyName() + "." + columnMpping.getPropertyName(),
                        columnMpping.getRepositoryFieldName()));
            }
            setColumnMapping(columnMpping, bp);
            mapping.add(columnMpping);
        }
    }

    private <T> void checkMapping(BeanDescriptor<T> bd, Map<String, PropertyMapping> tableMapping, Table table) {
        Map<String, PropertyMapping> fieldPropertyMap = getFieldProperyMap(tableMapping);
        fieldPropertyMap.forEach((fieldName, property) -> {
            if (!table.hasColumn(fieldName)) {
                throw new JdbcMappingException("#field.not.exists",
                        new Object[] { bd.getType().getName(), property.getPropertyName(), fieldName });
            }
        });
    }

    private Map<String, PropertyMapping> getFieldProperyMap(Map<String, PropertyMapping> tableMapping) {
        Map<String, PropertyMapping> nameSet = new HashMap<>();
        tableMapping.forEach((k, v) -> {
            if (Lang.isNotEmpty(k)) {
                nameSet.put(k, v);
            } else {
                if (Lang.isNotEmpty(v.getPropertyMappings())) {
                    v.getPropertyMappings().forEach(pm -> {
                        nameSet.put(pm.getRepositoryFieldName(), pm);
                    });
                }
            }
        });
        return nameSet;
    }

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

    private Table getMappingTable(String tableName) {
        Table tm = metadata.getTable(tableName);
        if (checkMapping && tm == null) {
            throw new JdbcMappingException("#talbe.not.exists", new Object[] { tableName });
        }
        return tm;
    }

    /**
     * 返回checkMapping
     *
     * @return checkMapping
     */
    public boolean isCheckMapping() {
        return checkMapping;
    }

    /**
     * 设置checkMapping
     *
     * @param checkMapping checkMapping
     */
    public void setCheckMapping(boolean checkMapping) {
        this.checkMapping = checkMapping;
    }
}
