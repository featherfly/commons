
package cn.featherfly.common.db.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * AbstractTable
 * </p>
 * .
 *
 * @author zhongj
 * @param <C> the generic type
 */
public abstract class AbstractTable<C extends Column> implements Table {
    /** The type. */
    protected String type;

    /** The name. */
    protected String name;

    /** The remark. */
    protected String remark;

    /** The catalog. */
    protected String catalog;

    protected String schema;

    /** The primary columns. */
    protected List<Column> primaryColumns = new ArrayList<>(1);

    /** The column map. */
    protected Map<String, Column> columnMap = new LinkedHashMap<>(0);

    /**
     * Instantiates a new table model.
     */
    public AbstractTable() {
    }

    /**
     * Adds the.
     *
     * @param column the column
     */
    protected void add(C column) {
        if (column.isPrimaryKey()) {
            primaryColumns.add(column);
        }
        columnMap.put(column.getName().toUpperCase(), column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasColumn(String columnName) {
        if (Lang.isEmpty(columnName)) {
            return false;
        }
        return columnMap.containsKey(columnName.toUpperCase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (catalog == null ? 0 : catalog.hashCode());
        result = prime * result + (schema == null ? 0 : schema.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (remark == null ? 0 : remark.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        result = prime * result + (columnMap == null ? 0 : columnMap.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Table)) {
            return false;
        }
        Table other = (Table) obj;
        if (!Lang.equals(catalog, other.getCatalog())) {
            return false;
        }
        if (!Lang.equals(schema, other.getSchema())) {
            return false;
        }
        if (!Lang.equals(columnMap, other.getColumnMap())) {
            return false;
        }
        if (!Lang.equals(name, other.getName())) {
            return false;
        }
        if (!Lang.equals(remark, other.getRemark())) {
            return false;
        }
        if (!Lang.equals(type, other.getType())) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Table [type=" + type + ", name=" + name + ", remark=" + remark + ", catalog=" + catalog + ", schema="
                + schema + ", columnMap=" + columnMap + "]";
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
     * 返回name.
     *
     * @return name
     */
    @Override
    public String getName() {
        return name;
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
     * 返回catalog.
     *
     * @return catalog
     */
    @Override
    public String getCatalog() {
        return catalog;
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

    /**
     * 返回schema
     *
     * @return schema
     */
    @Override
    public String getSchema() {
        return schema;
    }
}
