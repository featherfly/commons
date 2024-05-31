
package cn.featherfly.common.db.mapping;

import java.util.ArrayList;
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
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.bean.PropertyAccessorFactory;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.jpa.Comment;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.repository.Index;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PropertyMapping.Mode;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * StrictJdbcMappingFactory.
 *
 * @author zhongj
 */
public class StrictJdbcMappingFactory extends AbstractJdbcMappingFactory {

    /**
     * Instantiates a new strict jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param propertyAccessorFactory the property accessor factory
     */
    public StrictJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, PropertyAccessorFactory propertyAccessorFactory) {
        this(metadata, dialect, sqlTypeMappingManager, null, null, propertyAccessorFactory);
    }

    /**
     * Instantiates a new strict jdbc mapping factory.
     *
     * @param metadata the metadata
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param classNameConversions the class name conversions
     * @param propertyNameConversions the property name conversions
     * @param propertyAccessorFactory the property accessor factory
     */
    public StrictJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
        List<PropertyNameConversion> propertyNameConversions, PropertyAccessorFactory propertyAccessorFactory) {
        super(metadata, dialect, sqlTypeMappingManager, classNameConversions, propertyNameConversions,
            propertyAccessorFactory);
        setCheckMapping(true);
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

    @SuppressWarnings("unchecked")
    @Override
    protected <T> JdbcClassMapping<T> createClassMapping(Class<T> type) {
        Map<String, JdbcPropertyMapping> tableMapping = new LinkedHashMap<>();
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

        // YUFEI_TEST 需要测试看classLoader是否正常
        PropertyAccessor<
            T> propertyAccessor = propertyAccessorFactory.create(type, Thread.currentThread().getContextClassLoader());
        boolean findPk = false;
        int pkNo = 0;
        for (BeanProperty<?, ?> beanProperty : bd.getBeanProperties()) {
            if (mappingWithJpa(tableName, (BeanProperty<Object, ?>) beanProperty, tableMapping,
                (PropertyAccessor<Object>) propertyAccessor, logInfo)) {
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

        // 检查tableMapping
        checkTableMapping(tableMapping);

        // 形成映射对象
        JdbcClassMapping<T> classMapping = new JdbcClassMapping<>(type, tableName, schema, remark);
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
        logger.debug("class mapping {}", classMapping.toString());
        return classMapping;
    }

    private boolean mappingWithJpa(String tableName, BeanProperty<Object, ?> beanProperty,
        Map<String, JdbcPropertyMapping> tableMapping, PropertyAccessor<Object> propertyAccessor,
        StringBuilder logInfo) {
        if (isTransient(beanProperty, logInfo)) {
            return false;
        }
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        JdbcPropertyMapping mapping = new JdbcPropertyMapping();

        Embedded embedded = beanProperty.getAnnotation(Embedded.class);
        if (embedded != null) {
            mappinEmbedded(mapping, beanProperty, propertyAccessor, logInfo);
            tableMapping.put(mapping.getRepositoryFieldName(), mapping);
        } else {
            String columnName = getMappingColumnName(beanProperty);
            if (Lang.isNotEmpty(columnName)) {
                columnName = dialect.convertTableOrColumnName(columnName);
                setJavaSqlTypeMapper(mapping, beanProperty);
                setPropertyMapping(mapping, beanProperty);
                mapping.setSetter(propertyAccessor.getProperty(beanProperty.getIndex())::set);
                mapping.setGetter(propertyAccessor.getProperty(beanProperty.getIndex())::get);
                if (isPk) {
                    setIdGenerator(mapping, beanProperty, tableName, columnName);
                }
                ManyToOne manyToOne = beanProperty.getAnnotation(ManyToOne.class);
                OneToOne oneToOne = beanProperty.getAnnotation(OneToOne.class);
                if (manyToOne != null || oneToOne != null) {
                    mapping.setRepositoryFieldName(columnName);
                    mappingForeignKey(mapping, beanProperty, columnName, propertyAccessor, logInfo);
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

    private void mappinEmbedded(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty,
        PropertyAccessor<Object> propertyAccessor, StringBuilder logInfo) {
        mapping.setMode(Mode.EMBEDDED);
        setPropertyMapping(mapping, beanProperty);
        mapping.setSetter(propertyAccessor.getProperty(beanProperty.getIndex())::set);
        mapping.setGetter(propertyAccessor.getProperty(beanProperty.getIndex())::get);
        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
        for (BeanProperty<?, ?> bp : bd.getBeanProperties()) {
            if (isTransient(bp, logInfo)) {
                continue;
            }
            String columnName = getMappingColumnName(bp);
            columnName = dialect.convertTableOrColumnName(columnName);
            JdbcPropertyMapping columnMapping = new JdbcPropertyMapping();
            columnMapping.setRepositoryFieldName(columnName);
            setJavaSqlTypeMapper(columnMapping, bp);
            setPropertyMapping(columnMapping, bp);
            columnMapping.setSetter((obj, value) -> propertyAccessor.setPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }, value));
            columnMapping.setGetter(obj -> propertyAccessor.getPropertyValue(obj,
                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }));
            if (logger.isDebugEnabled()) {
                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
                    mapping.getPropertyName() + "." + columnMapping.getPropertyName(),
                    columnMapping.getRepositoryFieldName()));
            }
            setColumnMapping(columnMapping, bp);
            mapping.add(columnMapping);
        }
    }

    //    private void mappingForeignKey(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty, String columnName,
    //        PropertyAccessor<Object> propertyAccessor, StringBuilder logInfo) {
    //        mapping.setMode(Mode.MANY_TO_ONE);
    //        BeanDescriptor<?> bd = BeanDescriptor.getBeanDescriptor(beanProperty.getType());
    //        Collection<BeanProperty<?, ?>> bps = bd.findBeanPropertys(new BeanPropertyAnnotationMatcher(Id.class));
    //        if (Lang.isEmpty(bps)) {
    //            throw new JdbcMappingException("#no.id.property", new Object[] { beanProperty.getType().getName() });
    //        }
    //        for (BeanProperty<?, ?> bp : bps) {
    //            JdbcPropertyMapping columnMapping = new JdbcPropertyMapping();
    //            columnMapping.setRepositoryFieldName(columnName);
    //            setJavaSqlTypeMapper(columnMapping, bp);
    //            setPropertyMapping(columnMapping, bp);
    //            columnMapping.setSetter((obj, value) -> propertyAccessor.setPropertyValue(obj,
    //                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }, value));
    //            columnMapping.setGetter(obj -> propertyAccessor.getPropertyValue(obj,
    //                new int[] { mapping.getPropertyIndex(), columnMapping.getPropertyIndex() }));
    //            columnMapping.setPrimaryKey(mapping.getPrimaryKey());
    //            if (logger.isDebugEnabled()) {
    //                logInfo.append(String.format("%s###\t%s -> %s", SystemPropertyUtils.getLineSeparator(),
    //                    mapping.getPropertyName() + "." + columnMapping.getPropertyName(),
    //                    columnMapping.getRepositoryFieldName()));
    //            }
    //            setColumnMapping(columnMapping, bp);
    //            mapping.add(columnMapping);
    //        }
    //    }

    private <T> void checkMapping(BeanDescriptor<T> bd, Map<String, JdbcPropertyMapping> tableMapping, Table table) {
        Map<String, JdbcPropertyMapping> fieldPropertyMap = getFieldProperyMap(tableMapping);
        fieldPropertyMap.forEach((fieldName, property) -> {
            if (!table.hasColumn(fieldName)) {
                throw new JdbcMappingException("#field.not.exists",
                    new Object[] { bd.getType().getName(), property.getPropertyName(), fieldName });
            }
        });
    }

    private Map<String, JdbcPropertyMapping> getFieldProperyMap(Map<String, JdbcPropertyMapping> tableMapping) {
        Map<String, JdbcPropertyMapping> nameSet = new HashMap<>();
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
}
