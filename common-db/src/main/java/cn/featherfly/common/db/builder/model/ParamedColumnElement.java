
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

    /** The ignore strategy. */
    protected Predicate<Object> ignoreStrategy;

    /**
     * Instantiates a new paramed column element.
     *
     * @param dialect        dialect
     * @param name           name
     * @param param          param
     * @param ignoreStrategy the ignore strategy
     */
    public ParamedColumnElement(Dialect dialect, String name, Object param, Predicate<?> ignoreStrategy) {
        this(dialect, name, param, null, ignoreStrategy);
    }

    /**
     * Instantiates a new paramed column element.
     *
     * @param dialect        dialect
     * @param name           name
     * @param param          param
     * @param tableAlias     tableAlias
     * @param ignoreStrategy the ignore strategy
     */
    public ParamedColumnElement(Dialect dialect, String name, Object param, String tableAlias,
            Predicate<?> ignoreStrategy) {
        super(dialect, name, tableAlias);
        this.param = param;
        setIgnoreStrategy(ignoreStrategy);
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
     * get ignoreStrategy value
     *
     * @return ignoreStrategy
     */
    public Predicate<?> getIgnoreStrategy() {
        return ignoreStrategy;
    }

    /**
     * set ignoreStrategy value
     *
     * @param ignoreStrategy ignoreStrategy
     */
    @SuppressWarnings("unchecked")
    public void setIgnoreStrategy(Predicate<?> ignoreStrategy) {
        AssertIllegalArgument.isNotNull(ignoreStrategy, "ignoreStrategy");
        this.ignoreStrategy = (Predicate<Object>) ignoreStrategy;
    }
}
