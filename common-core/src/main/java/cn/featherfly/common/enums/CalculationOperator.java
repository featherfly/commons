package cn.featherfly.common.enums;

/**
 * Calculation Operator.
 *
 * @author zhongj
 */
public enum CalculationOperator implements Operator {
    /**
     * 加法 +
     */
    PLUS("+"),
    /**
     * 减法 -
     */
    SUBTRACT("-"),
    /**
     * 乘法 *
     */
    MULTIPLY("x"),
    /**
     * 除法 /
     */
    DIVIDE("÷");

    private String symbol;

    CalculationOperator(String symbol) {
        this.symbol = symbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbol() {
        return symbol;
    }
}
