
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

    /** 不以XX开始. */
    NSW,

    /** 包含. */
    CO,

    /** 不包含. */
    NCO,

    /** 以XX结尾. */
    EW,

    /** 不以XX结尾. */
    NEW,

    /** 为null. */
    ISN,

    /** 不为null. */
    INN,

    /** in. */
    IN,

    /** not in. */
    NI,

    /** like. */
    LK,

    /** not like. */
    NL,

    /** between and. */
    BA,

    /** not between and. */
    NBA

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
