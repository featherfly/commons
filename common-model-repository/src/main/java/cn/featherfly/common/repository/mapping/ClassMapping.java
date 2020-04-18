package cn.featherfly.common.repository.mapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * class repository mapping
 * </p>
 *
 * @param <T> 类型
 * @author zhongj
 */
public class ClassMapping<T> {

    /**
     * @param type           类型
     * @param repositoryName 存储名
     */
    public ClassMapping(Class<T> type, String repositoryName) {
        this.type = type;
        this.repositoryName = repositoryName;
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
        for (PropertyMapping pm : propertyMappings.values()) {
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
     *
     * @return 所有属性映射
     */
    public Collection<PropertyMapping> getPropertyMappings() {
        return propertyMappings.values();
    }

    /**
     * <p>
     * 返回所有主键属性映射
     * </p>
     *
     * @return 所有属性映射
     */
    public List<PropertyMapping> getPrivaryKeyPropertyMappings() {
        return propertyMappings.values().stream().filter(p -> p.isPrimaryKey()).collect(Collectors.toList());
    }

    public void addPropertyMapping(PropertyMapping propertyMapping) {
        propertyMappings.put(propertyMapping.getPropertyName(), propertyMapping);
    }

    public void addPropertyMappings(Collection<PropertyMapping> propertyMappings) {
        for (PropertyMapping propertyMapping : propertyMappings) {
            addPropertyMapping(propertyMapping);
        }
    }

    // ********************************************************************
    //
    // ********************************************************************

    private Map<String, PropertyMapping> propertyMappings = new HashMap<>(0);

    private String repositoryName;

    private Class<?> type;

    /**
     * @return 返回tableName
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
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
}
