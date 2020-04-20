
package cn.featherfly.common.db.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;

/**
 * <p>
 * AbstractTable
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractTable<T extends Table> implements Table {
    /** The type. */
    protected String type;

    /** The name. */
    protected String name;

    /** The remark. */
    protected String remark;

    /** The catalog. */
    protected String catalog;

    /** The primary columns. */
    protected List<Column> primaryColumns = new ArrayList<>(1);

    /** The column map. */
    protected Map<String, Column> columnMap = new LinkedHashMap<>(0);

    /**
     * Instantiates a new table model.
     */
    public AbstractTable() {
    }

    protected void add(Column column) {
        if (column.isPrimaryKey()) {
            primaryColumns.add(column);
        }
        columnMap.put(column.getName().toUpperCase(), column);
    }

    /**
     * 返回type.
     *
     * @return type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * 设置type.
     *
     * @param type type
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T setType(String type) {
        this.type = type;
        return (T) this;
    }

    /**
     * 返回name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置name.
     *
     * @param name name
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * 返回remark.
     *
     * @return remark
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark.
     *
     * @param remark remark
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T setRemark(String remark) {
        this.remark = remark;
        return (T) this;
    }

    /**
     * 返回catalog.
     *
     * @return catalog
     */
    @Override
    public String getCatalog() {
        return catalog;
    }

    /**
     * 设置catalog.
     *
     * @param catalog catalog
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T setCatalog(String catalog) {
        this.catalog = catalog;
        return (T) this;
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
        return new ArrayList<>(columnMap.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Column> getColumnMap() {
        return new LinkedHashMap<>(columnMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Column> getPrimaryColumns() {
        return new ArrayList<>(primaryColumns);
    }
}
