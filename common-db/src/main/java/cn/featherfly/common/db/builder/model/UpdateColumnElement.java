
package cn.featherfly.common.db.builder.model;

import java.util.function.Predicate;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * UpdateColumnElement .
 *
 * @author zhongj
 */
public class UpdateColumnElement extends ParamedColumnElement {

    /** The set type. */
    protected SetType setType;

    /**
     * Instantiates a new update column element.
     *
     * @param dialect the dialect
     * @param name the name
     * @param param the param
     * @param ignoreStrategy the ignore strategy
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param, Predicate<?> ignoreStrategy) {
        this(dialect, null, name, param, ignoreStrategy);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect dialect
     * @param tableAlias tableAlias
     * @param name name
     * @param param param
     * @param ignoreStrategy the ignore strategy
     */
    public UpdateColumnElement(Dialect dialect, String tableAlias, String name, Object param,
        Predicate<?> ignoreStrategy) {
        this(dialect, tableAlias, name, param, null, ignoreStrategy);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect dialect
     * @param tableAlias tableAlias
     * @param name name
     * @param param param
     * @param setType setType
     * @param ignoreStrategy the ignore strategy
     */
    public UpdateColumnElement(Dialect dialect, String tableAlias, String name, Object param, SetType setType,
        Predicate<?> ignoreStrategy) {
        super(dialect, tableAlias, name, param, ignoreStrategy);
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
        if (param instanceof SqlElement) {
            SqlElement sqlElement = (SqlElement) param;
            String columnName = dialect.dml().column(getTableAlias(), getName());
            switch (setType) {
                case INCR:
                    return columnName + " = " + columnName + " + " + sqlElement;
                case DECR:
                    return columnName + " = " + columnName + " - " + sqlElement;
                default:
                    return columnName + " = " + sqlElement;
            }
        } else if (ignore(param)) { // 忽略
            return "";
        } else {
            String columnName = dialect.dml().column(getTableAlias(), getName());
            switch (setType) {
                case INCR:
                    return columnName + " = " + columnName + " + ?";
                case DECR:
                    return columnName + " = " + columnName + " - ?";
                default:
                    return columnName + " = ?";
            }
        }
    }

    /**
     * The Enum SetType.
     *
     * @author zhongj
     */
    public enum SetType {

        /** The set. */
        SET,

        /** The incr. */
        INCR,

        /** The decr. */
        DECR
    }
}
