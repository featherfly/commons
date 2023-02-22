
package cn.featherfly.common.db.model;

import cn.featherfly.common.db.Column;

/**
 * ReadonlyTable.
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
}
