
package cn.featherfly.common.db.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Index;

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

    /** The schema. */
    protected String schema;

    /** The primary columns. */
    protected List<Column> primaryColumns = new ArrayList<>(1);

    /** The column map. */
    protected Map<String, Column> columnMap = new LinkedHashMap<>(0);

    /** The index map. */
    protected Map<String, Index> indexMap = new LinkedHashMap<>();

    /**
     * Instantiates a new table model.
     */
    public AbstractTable() {
    }

    /**
     * Adds the column.
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
     * Adds the index.
     *
     * @param index the index
     */
    protected void add(Index index) {
        indexMap.put(index.getName().toUpperCase(), index);
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
        result = prime * result + (indexMap == null ? 0 : indexMap.hashCode());
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
        if (!Lang.equals(indexMap, other.getColumnMap())) {
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
                + schema + ", columns=" + columnMap + ", indexs=" + indexMap + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
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
        return Lang.isEmpty(columnName) ? null : columnMap.get(columnName.toUpperCase());
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
     * {@inheritDoc}
     */
    @Override
    public String getSchema() {
        return schema;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexs() {
        return new ArrayList<>(indexMap.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Index> getIndexMap() {
        return new LinkedHashMap<>(indexMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Index getIndex(String indexName) {
        return Lang.isEmpty(indexName) ? null : indexMap.get(indexName.toUpperCase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasIndex(String indexName) {
        return Lang.isEmpty(indexName) ? false : indexMap.containsKey(indexName.toUpperCase());
    }
}
