
package cn.featherfly.common.operator;

/**
 * comparison operators.
 *
 * @author zhongj
 */
public enum ComparisonOperator implements Operator {

    /** 小于. */
    LT,

    /** 小于等于. */
    LE,

    /** 等于. */
    EQ,

    /** 不等于. */
    NE,

    /** 大于等于. */
    GE,

    /** 大于. */
    GT,

    /** 以XX开始. */
    SW,

    /** 包含. */
    CO,

    /** 以XX结尾. */
    EW,

    /** 为null. */
    ISN,

    /** 不为null. */
    INN,

    /** in. */
    IN,

    /** not in. */
    NIN,

    /** like. */
    LK
    //
    ;

    ComparisonOperator() {
    }

    /**
     * The Enum like query match strategy.
     *
     * @author zhongj
     */
    public enum MatchStrategy {

        /** 自动，由具体的存储（数据库）的默认值决定. */
        AUTO,

        /** 不区分大小写. */
        CASE_INSENSITIVE,

        /** 区分大小写. */
        CASE_SENSITIVE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbol() {
        return name();
    }
}
