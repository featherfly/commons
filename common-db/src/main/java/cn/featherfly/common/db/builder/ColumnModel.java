
package cn.featherfly.common.db.builder;

import cn.featherfly.common.db.model.AbstractColumnPojo;

/**
 * ColumnModel .
 *
 * @author zhongj
 */
public class ColumnModel extends AbstractColumnPojo<ColumnModel> {

    /**
     * Instantiates a new column model.
     */
    public ColumnModel() {
        super();
    }

    /**
     * Instantiates a new column model.
     *
     * @param name the name
     */
    public ColumnModel(String name) {
        super(name);
    }

}
