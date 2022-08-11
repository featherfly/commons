
package cn.featherfly.common.repository.operate;

/**
 * <p>
 * query condition operator
 * </p>
 * .
 *
 * @author zhongj
 */
public enum QueryOperator implements Operator {

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

    // /**
    // * 按位与 ： &
    // */
    // ba("&"),
    // /**
    // * 按位或：|
    // */
    // bo("|"),
    // /**
    // * 按位异或:^
    // */
    // bx("^"),
    // /**
    // * 按位取反:~
    // */
    // bn("~"),
    // /**
    // * 按位左移:<<
    // */
    // bl("<<"),
    // /**
    // * 按位右移：>>
    // */
    /**
     * Instantiates a new query operator.
     */
    // br(">>")
    ;

    QueryOperator() {
    }

    /**
     * The Enum LikeQueryPolicy.
     *
     * @author zhongj
     */
    public enum QueryPolicy {

        /** 自动，由具体的存储（数据库）的默认值决定. */
        AUTO,

        /** 不区分大小写. */
        CASE_INSENSITIVE,

        /** 区分大小写. */
        CASE_SENSITIVE;
    }
}
