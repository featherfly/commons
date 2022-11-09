
package cn.featherfly.common.operator;

/**
 * <p>
 * sort operator
 * </p>
 *
 * @author zhongj
 */
public enum SortOperator implements Operator {
    /**
     * 升序
     */
    ASC,
    /**
     * 降序
     */
    DESC;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbol() {
        return name();
    }
}
