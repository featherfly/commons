
package cn.featherfly.common.db.builder.model;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * UpdateColumnElement
 * </p>
 * .
 *
 * @author zhongj
 */
public class UpdateColumnElement extends ParamedColumnElement {

    /** The set type. */
    protected SetType setType;

    /**
     * Instantiates a new update column element.
     *
     * @param dialect dialect
     * @param name    name
     * @param param   param
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param) {
        this(dialect, name, param, null);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect    dialect
     * @param name       name
     * @param param      param
     * @param tableAlias tableAlias
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param, String tableAlias) {
        this(dialect, name, param, tableAlias, null);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect    dialect
     * @param name       name
     * @param param      param
     * @param tableAlias tableAlias
     * @param setType    setType
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param, String tableAlias, SetType setType) {
        super(dialect, name, param, tableAlias);
        if (setType == null) {
            this.setType = SetType.SET;
        } else {
            this.setType = setType;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        String columnName = dialect.buildColumnSql(getName(), getTableAlias());
        if (setType == SetType.SET) {
            return columnName + " = ?";
        } else {
            return columnName + " = " + columnName + " + ?";
        }
    }

    /**
     * The Enum SetType.
     */
    public enum SetType {

        /** The set. */
        SET,

        /** The incr. */
        INCR
    }
}
