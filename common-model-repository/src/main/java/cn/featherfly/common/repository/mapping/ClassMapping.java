package cn.featherfly.common.repository.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Index;

/**
 * <p>
 * class repository mapping
 * </p>
 * .
 *
 * @author zhongj
 * @param <T> 类型
 */
public class ClassMapping<T, P extends PropertyMapping<P>> {

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
    public P getPropertyMapping(String propertyName) {
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
    public P getPropertyMappingByPersitField(String persitField) {
        for (P pm : getPropertyMappingLeafNodes()) {
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
    public Collection<P> getPropertyMappings() {
        return new ArrayList<>(propertyMappings.values());
    }

    /**
     * 返回所有叶节点的属性映射.
     *
     * @return the property mapping leaf nodes
     */
    public Collection<P> getPropertyMappingLeafNodes() {
        List<P> leafNodes = new ArrayList<>();
        for (P propertyMapping : getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().size() > 0) {
                for (P pm : propertyMapping.getPropertyMappings()) {
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
    public List<P> getPrivaryKeyPropertyMappings() {
        List<P> pks = new ArrayList<>();
        for (P pk : propertyMappings.values().stream().filter(p -> p.isPrimaryKey()).collect(Collectors.toList())) {
            if (Lang.isEmpty(pk.getPropertyMappings())) {
                pks.add(pk);
            } else {
                for (P spk : pk.getPropertyMappings()) {
                    if (spk.isPrimaryKey()) {
                        pks.add(spk);
                    }
                }
            }
        }
        return pks;
    }

    /**
     * Adds the property mapping.
     *
     * @param propertyMapping the property mapping
     */
    public void addPropertyMapping(P propertyMapping) {
        propertyMappings.put(propertyMapping.getPropertyName(), propertyMapping);
    }

    /**
     * Adds the property mappings.
     *
     * @param propertyMappings the property mappings
     */
    public void addPropertyMappings(Collection<P> propertyMappings) {
        for (P propertyMapping : propertyMappings) {
            addPropertyMapping(propertyMapping);
        }
    }

    /**
     * Adds the index.
     *
     * @param index the index
     */
    public void addIndex(Index index) {
        this.indexs.put(index.getName(), index);
    }

    /**
     * Adds the indexs.
     *
     * @param indexs the indexs
     */
    public void addIndexs(Collection<Index> indexs) {
        for (Index index : indexs) {
            addIndex(index);
        }
    }

    /**
     * 返回indexs.
     *
     * @return indexs
     */
    public List<Index> getIndexs() {
        return new ArrayList<>(indexs.values());
    }

    /**
     * Gets the index.
     *
     * @param indexName the index name
     * @return the index
     */
    public Index getIndex(String indexName) {
        return indexs.get(indexName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ClassMapping [repositoryName=" + repositoryName + ", remark=" + remark + ", schema=" + schema
                + ", type=" + type + ", indexs=" + indexs + "propertyMappings=" + propertyMappings + "]";
    }

    // ********************************************************************
    //
    // ********************************************************************

    /** The property mappings. */
    private Map<String, P> propertyMappings = new LinkedHashMap<>(0);

    /** The indexs. */
    private Map<String, Index> indexs = new LinkedHashMap<>(0);

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
     * 返回remark.
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 返回schema.
     *
     * @return schema
     */
    public String getSchema() {
        return schema;
    }
}
