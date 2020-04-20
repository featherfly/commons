
package cn.featherfly.common.db.builder;

import java.util.Collection;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.model.AbstractTable;

/**
 * <p>
 * TableModel
 * </p>
 * .
 *
 * @author zhongj
 */
public class TableModel extends AbstractTable<TableModel> {

    /**
     * Instantiates a new table model.
     */
    public TableModel() {
    }

    /**
     * <p>
     * 添加列
     * </p>
     * .
     *
     * @param column column
     * @return the table model
     */
    public TableModel addColumn(Column column) {
        if (column.isPrimaryKey()) {
            primaryColumns.add(column);
        }
        columnMap.put(column.getName().toUpperCase(), column);
        return this;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象数组
     * @return the table model
     */
    public TableModel addColumn(Column... columns) {
        for (Column columnMetadata : columns) {
            addColumn(columnMetadata);
        }
        return this;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象集合
     * @return the table model
     */
    public TableModel addColumn(Collection<Column> columns) {
        for (Column columnMetadata : columns) {
            addColumn(columnMetadata);
        }
        return this;
    }
}
