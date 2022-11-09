package cn.featherfly.common.enums;

/**
 * logic operator.
 *
 * @author zhongj
 */
public enum LogicOperator implements Operator {
    /**
     * 并集
     */
    AND,
    /**
     * 交集
     */
    OR;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbol() {
        return name();
    }
}