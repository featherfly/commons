
package cn.featherfly.common.db.builder.model;

import java.util.function.Predicate;

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
    public UpdateColumnElement(Dialect dialect, String name, Object param, Predicate<?> ignoreStrategy) {
        this(dialect, name, param, null, ignoreStrategy);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect        dialect
     * @param name           name
     * @param param          param
     * @param tableAlias     tableAlias
     * @param ignoreStrategy the ignore strategy
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param, String tableAlias,
            Predicate<?> ignoreStrategy) {
        this(dialect, name, param, tableAlias, null, ignoreStrategy);
    }

    /**
     * Instantiates a new update column element.
     *
     * @param dialect        dialect
     * @param name           name
     * @param param          param
     * @param tableAlias     tableAlias
     * @param setType        setType
     * @param ignoreStrategy the ignore strategy
     */
    public UpdateColumnElement(Dialect dialect, String name, Object param, String tableAlias, SetType setType,
            Predicate<?> ignoreStrategy) {
        super(dialect, name, param, tableAlias, ignoreStrategy);
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
        if (ignoreStrategy.test(param)) { // 忽略
            return "";
        }
        String columnName = dialect.buildColumnSql(getTableAlias(), getName());
        if (setType == SetType.SET) {
            return columnName + " = ?";
        } else {
            return columnName + " = " + columnName + " + ?";
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
        INCR
    }
}
