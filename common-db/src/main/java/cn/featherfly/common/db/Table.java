
package cn.featherfly.common.db;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Table
 * </p>
 *
 * @author zhongj
 */
public interface Table {

    String getName();

    String getType();

    String getRemark();

    String getCatalog();

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
     * @param columnName 表名称
     * @return 列元数据对象
     */
    Column getColumn(String columnName);
}
