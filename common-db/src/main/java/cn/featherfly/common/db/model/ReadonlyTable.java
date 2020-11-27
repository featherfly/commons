
package cn.featherfly.common.db.model;

import cn.featherfly.common.db.Column;

/**
 * <p>
 * ReadonlyTable
 * </p>
 *
 * @author zhongj
 */
public class ReadonlyTable extends AbstractTable<Column> {

    /**
     * Instantiates a new readonly table.
     *
     * @param type    the type
     * @param name    the name
     * @param remark  the remark
     * @param catalog the catalog
     * @param schema  the schema
     */
    public ReadonlyTable(String type, String name, String remark, String catalog, String schema) {
        super();
        this.type = type;
        this.name = name;
        this.remark = remark;
        this.catalog = catalog;
        this.schema = schema;
    }

    /**
     * 设置type.
     *
     * @param type type
     * @return the table model
     */
    protected ReadonlyTable setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 设置name.
     *
     * @param name name
     * @return the table model
     */
    protected ReadonlyTable setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 设置remark.
     *
     * @param remark remark
     * @return the table model
     */
    protected ReadonlyTable setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    /**
     * 设置catalog.
     *
     * @param catalog catalog
     * @return the table model
     */
    protected ReadonlyTable setCatalog(String catalog) {
        this.catalog = catalog;
        return this;
    }

    /**
     * 设置schema.
     *
     * @param schema schema
     * @return the table model
     */
    protected ReadonlyTable setSchema(String schema) {
        this.schema = schema;
        return this;
    }
}
