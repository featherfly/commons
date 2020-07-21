
package cn.featherfly.common.db.model;

import java.util.Collection;

/**
 * <p>
 * AbstractTable
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractTablePojo<T extends AbstractTablePojo<T, C>, C extends AbstractColumnPojo<C>>
        extends AbstractTable<C> {

    /**
     * Instantiates a new table model.
     */
    public AbstractTablePojo() {
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
    @SuppressWarnings("unchecked")
    public T addColumn(C column) {
        super.add(column);
        column.setTable(this);
        return (T) this;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象数组
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T addColumn(C... columns) {
        for (C columnMetadata : columns) {
            addColumn(columnMetadata);
        }
        return (T) this;
    }

    /**
     * <p>
     * 添加列元数据.
     * </p>
     *
     * @param columns 列元数据对象集合
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T addColumn(Collection<C> columns) {
        for (C columnMetadata : columns) {
            addColumn(columnMetadata);
        }
        return (T) this;
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
     * 设置schema.
     *
     * @param schema schema
     * @return the table model
     */
    @SuppressWarnings("unchecked")
    public T setSchema(String schema) {
        this.schema = schema;
        return (T) this;
    }

}
