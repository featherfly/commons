
package cn.featherfly.common.operator;

/**
 * AggregateFunctions.
 *
 * @author zhongj
 */
public enum AggregateFunction implements Function {
    COUNT,
    SUM,
    MAX,
    MIN,
    AVG;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getParameterCount() {
        return 1;
    }
}
