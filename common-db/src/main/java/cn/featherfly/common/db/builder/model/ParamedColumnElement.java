
package cn.featherfly.common.db.builder.model;

import java.lang.reflect.Array;
import java.util.function.Predicate;

import cn.featherfly.common.db.FieldValueOperator;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        if (ignore(param)) { // ignore
            return "";
        } else {
            return super.toSql();
        }
    }

    protected boolean ignore(Object value) {
        if (value == null) {
            return ignoreStrategy.test(value);
        }

        if (value instanceof FieldValueOperator) {
            return ignoreStrategy.test(((FieldValueOperator<?>) value).getValue());
        } else if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            if (length > 0) {
                Object first = Array.get(value, 0);
                first = unwrapFieldValueOperator(first);
                Object newArray = Array.newInstance(first.getClass(), length);
                Array.set(newArray, 0, first);
                for (int i = 1; i < length; i++) {
                    Array.set(newArray, i, unwrapFieldValueOperator(Array.get(value, i)));
                }
                return ignoreStrategy.test(newArray);
            } else {
                return ignoreStrategy.test(null); // 使用null而不适用空数组可以规避类型错误
            }
        } else {
            return ignoreStrategy.test(value);
        }
    }

    private Object unwrapFieldValueOperator(Object value) {
        return value instanceof FieldValueOperator ? ((FieldValueOperator<?>) value).getValue() : value;
    }
}
