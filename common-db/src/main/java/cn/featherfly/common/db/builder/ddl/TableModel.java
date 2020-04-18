
package cn.featherfly.common.db.builder.ddl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.metadata.Column;
import cn.featherfly.common.db.metadata.Table;

/**
 * <p>
 * TableModel
 * </p>
 *
 * @author zhongj
 */
public class TableModel implements Table {

    private String type;

    private String name;

    private String remark;

    private String catalog;

    private List<Column> primaryColumns = new ArrayList<>(1);

    private Map<String, Column> columnMap = new LinkedHashMap<>(0);

    /**
     * 返回type
     *
     * @return type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * 设置type
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 返回name
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回remark
     *
     * @return remark
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 返回catalog
     *
     * @return catalog
     */
    @Override
    public String getCatalog() {
        return catalog;
    }

    /**
     * 设置catalog
     *
     * @param catalog catalog
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Column getColumn(String columnName) {
        return columnMap.get(columnName.toUpperCase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Column> getColumns() {
        return columnMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Column> getColumnMap() {
        return columnMap;
    }

    /**
     * <p>
     * 添加列
     * </p>
     *
     * @param column column
     */
    public void addColumn(Column column) {
        if (column.isPrimaryKey()) {
            primaryColumns.add(column);
        }
        columnMap.put(column.getName().toUpperCase(), column);
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象数组
     */
    public void addColumn(Column... columns) {
        for (Column columnMetadata : columns) {
            addColumn(columnMetadata);
        }
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象集合
     */
    public void addColumn(Collection<Column> columns) {
        for (Column columnMetadata : columns) {
            addColumn(columnMetadata);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Column> getPrimaryColumns() {
        return primaryColumns;
    }

}
