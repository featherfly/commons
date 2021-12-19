
package cn.featherfly.common.db.builder.model;

import java.util.function.Predicate;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * ParamedColumnElement
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class ParamedColumnElement extends ColumnElement {

    /** The param. */
    protected Object param;

    /** The ignore policy. */
    protected Predicate<Object> ignorePolicy;

    /**
     * Instantiates a new paramed column element.
     *
     * @param dialect      dialect
     * @param name         name
     * @param param        param
     * @param ignorePolicy the ignore policy
     */
    public ParamedColumnElement(Dialect dialect, String name, Object param, Predicate<Object> ignorePolicy) {
        this(dialect, name, param, null, ignorePolicy);
    }

    /**
     * Instantiates a new paramed column element.
     *
     * @param dialect      dialect
     * @param name         name
     * @param param        param
     * @param tableAlias   tableAlias
     * @param ignorePolicy the ignore policy
     */
    public ParamedColumnElement(Dialect dialect, String name, Object param, String tableAlias,
            Predicate<Object> ignorePolicy) {
        super(dialect, name, tableAlias);
        AssertIllegalArgument.isNotNull(ignorePolicy, "ignorePolicy");
        this.param = param;
        this.ignorePolicy = ignorePolicy;
    }

    /**
     * 返回param.
     *
     * @return param
     */
    public Object getParam() {
        return param;
    }

    /**
     * 设置param.
     *
     * @param param param
     */
    public void setParam(Object param) {
        this.param = param;
    }

    /**
     * get ignorePolicy value.
     *
     * @return ignorePolicy
     */
    public Predicate<Object> getIgnorePolicy() {
        return ignorePolicy;
    }

    /**
     * set ignorePolicy value.
     *
     * @param ignorePolicy ignorePolicy
     */
    public void setIgnorePolicy(Predicate<Object> ignorePolicy) {
        this.ignorePolicy = ignorePolicy;
    }
}
