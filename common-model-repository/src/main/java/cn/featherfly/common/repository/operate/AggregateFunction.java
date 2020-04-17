
package cn.featherfly.common.repository.operate;

/**
 * <p>
 * AggregateFunctions
 * </p>
 *
 * @author zhongj
 */
public enum AggregateFunction implements Function {
    COUNT,
    SUM,
    MAX,
    MIN,
    AVG,
    DISTINCT;
}
