package cn.featherfly.common.repository.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * class repository mapping
 * </p>
 * .
 *
 * @author zhongj
 * @param <T> 类型
 */
public class ClassMapping<T> {

    /**
     * Instantiates a new class mapping.
     *
     * @param type           类型
     * @param repositoryName 存储名
     */
    public ClassMapping(Class<T> type, String repositoryName) {
        this(type, repositoryName, null);
    }

    /**
     * Instantiates a new class mapping.
     *
     * @param type           类型
     * @param repositoryName 存储名
     * @param schema         the schema
     */
    public ClassMapping(Class<T> type, String repositoryName, String schema) {
        this(type, repositoryName, schema, null);
    }

    /**
     * Instantiates a new class mapping.
     *
     * @param type           类型
     * @param repositoryName 存储名
     * @param schema         the schema
     * @param remark         remark
     */
    public ClassMapping(Class<T> type, String repositoryName, String schema, String remark) {
        this.type = type;
        this.repositoryName = repositoryName;
        this.schema = schema;
        this.remark = remark;
    }

    /**
     * <p>
     * 返回指定属性名称的属性映射. 没有找到返回null.
     * </p>
     *
     * @param propertyName 属性名称
     * @return 属性映射对象
     */
    public PropertyMapping getPropertyMapping(String propertyName) {
        return propertyMappings.get(propertyName);
    }

    /**
     * <p>
     * 通过持久化字段（数据库字段）的名称返回指定属性映射. 没有找到返回null.
     * </p>
     *
     * @param persitField 持久化字段（数据库字段）
     * @return 属性映射对象
     */
    public PropertyMapping getPropertyMappingByPersitField(String persitField) {
        for (PropertyMapping pm : getPropertyMappingLeafNodes()) {
            if (pm.getRepositoryFieldName().equals(persitField)) {
                return pm;
            }
        }
        return null;
    }

    /**
     * <p>
     * 返回所有属性映射
     * </p>
     * .
     *
     * @return 所有属性映射
     */
    public Collection<PropertyMapping> getPropertyMappings() {
        return propertyMappings.values();
    }

    /**
     * 返回所有叶节点的属性映射
     *
     * @return the property mapping leaf nodes
     */
    public Collection<PropertyMapping> getPropertyMappingLeafNodes() {
        List<PropertyMapping> leafNodes = new ArrayList<>();
        for (PropertyMapping propertyMapping : getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().size() > 0) {
                for (PropertyMapping pm : propertyMapping.getPropertyMappings()) {
                    leafNodes.add(pm);
                }
            } else {
                leafNodes.add(propertyMapping);
            }
        }
        return leafNodes;
    }

    /**
     * <p>
     * 返回所有主键属性映射
     * </p>
     * .
     *
     * @return 所有属性映射
     */
    public List<PropertyMapping> getPrivaryKeyPropertyMappings() {
        return propertyMappings.values().stream().filter(p -> p.isPrimaryKey()).collect(Collectors.toList());
    }

    /**
     * Adds the property mapping.
     *
     * @param propertyMapping the property mapping
     */
    public void addPropertyMapping(PropertyMapping propertyMapping) {
        propertyMappings.put(propertyMapping.getPropertyName(), propertyMapping);
    }

    /**
     * Adds the property mappings.
     *
     * @param propertyMappings the property mappings
     */
    public void addPropertyMappings(Collection<PropertyMapping> propertyMappings) {
        for (PropertyMapping propertyMapping : propertyMappings) {
            addPropertyMapping(propertyMapping);
        }
    }

    // ********************************************************************
    //
    // ********************************************************************

    /** The property mappings. */
    private Map<String, PropertyMapping> propertyMappings = new LinkedHashMap<>(0);

    /** The repository name. */
    private String repositoryName;

    /** The remark. */
    private String remark;

    private String schema;

    /** The type. */
    private Class<?> type;

    /**
     * Gets the repository name.
     *
     * @return 返回tableName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * Gets the type.
     *
     * @return 返回type
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ClassMapping [repositoryName=" + repositoryName + ", type=" + type + ", propertyMappings="
                + propertyMappings + "]";
    }

    /**
     * 返回remark.
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 返回schema
     *
     * @return schema
     */
    public String getSchema() {
        return schema;
    }
}
