
package cn.featherfly.common.db;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.repository.Index;
import cn.featherfly.common.repository.Repository;

/**
 * The Interface Table.
 *
 * @author zhongj
 */
public interface Table extends Repository {

    /**
     * {@inheritDoc}
     */
    @Override
    default String name() {
        return getName();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Gets the type.
     *
     * @return the type
     */
    String getType();

    /**
     * Gets the remark.
     *
     * @return the remark
     */
    String getRemark();

    /**
     * Gets the catalog.
     *
     * @return the catalog
     */
    String getCatalog();

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    String getSchema();

    /**
     * Gets the indexs.
     *
     * @return the indexs
     */
    List<Index> getIndexs();

    /**
     * <p>
     * 返回所有主键列元数据对象的列表.
     * </p>
     *
     * @return 所有主键列元数据对象的列表.
     */
    List<Column> getPrimaryColumns();

    /**
     * <p>
     * 返回所有列元数据对象的集合.
     * </p>
     *
     * @return 所有列元数据对象的集合
     */
    Collection<Column> getColumns();

    /**
     * <p>
     * 返回所有列元数据对象的MAP.
     * </p>
     *
     * @return 所有列元数据对象的MAP
     */
    Map<String, Column> getColumnMap();

    /**
     * <p>
     * 返回指定名称的列元数据对象. 没有找到返回null.
     * </p>
     *
     * @param columnName 列名称
     * @return 列元数据对象
     */
    Column getColumn(String columnName);

    /**
     * hasColumn.
     *
     * @param columnName columnName
     * @return boolean
     */
    boolean hasColumn(String columnName);

    /**
     * <p>
     * 返回所有索引元数据对象的MAP.
     * </p>
     *
     * @return 所有索引元数据对象的MAP
     */
    Map<String, Index> getIndexMap();

    /**
     * <p>
     * 返回指定名称的索引元数据对象. 没有找到返回null.
     * </p>
     *
     * @param indexName 索引名称
     * @return 列元数据对象
     */
    Index getIndex(String indexName);

    /**
     * hasIndex.
     *
     * @param indexName indexName
     * @return boolean
     */
    boolean hasIndex(String indexName);
}
