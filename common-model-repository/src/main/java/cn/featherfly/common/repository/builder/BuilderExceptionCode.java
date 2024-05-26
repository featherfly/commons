
package cn.featherfly.common.repository.builder;

import java.util.Locale;

/**
 * BuilderExceptionCode.
 *
 * @author zhongj
 */
public class BuilderExceptionCode extends cn.featherfly.common.exception.SimpleLocalizedExceptionCode {

    private static final String MODULE = "BUILDER";

    /**
     * Instantiates a new builder exception code.
     *
     * @param num num
     * @param key key
     */
    public BuilderExceptionCode(Integer num, String key) {
        this(num, key, new Object[] {});
    }

    /**
     * Instantiates a new builder exception code.
     *
     * @param num num
     * @param key key
     * @param argus argus
     */
    public BuilderExceptionCode(Integer num, String key, Object[] argus) {
        this(num, key, null, argus);
    }

    /**
     * Instantiates a new builder exception code.
     *
     * @param num num
     * @param key key
     * @param locale locale
     */
    public BuilderExceptionCode(Integer num, String key, Locale locale) {
        this(num, key, locale, new Object[] {});
    }

    /**
     * Instantiates a new builder exception code.
     *
     * @param num num
     * @param key key
     * @param locale locale
     * @param argus argus
     */
    public BuilderExceptionCode(Integer num, String key, Locale locale, Object[] argus) {
        super(MODULE, num, key, locale, argus);
    }

    /**
     * The Enum BuilderExceptionCodes.
     */
    public enum BuilderExceptionCodes {

        /** The builder10000. */
        BUILDER10000("query_operator_null", 10000),

        /** The builder10001. */
        BUILDER10001("no_condition_behind", 10001),

        /** The builder10003. */
        BUILDER10003("next_to_same_condition", 10003),

        /** The builder10004. */
        BUILDER10004("index_out_off_bounds_name_alias_size", 10004),

        /** The builder10005. */
        BUILDER10005("mulitiple_name", 10005);

        private String key;

        private Integer num;

        /**
         * @param key key
         * @param num num
         */
        BuilderExceptionCodes(String key, Integer num) {
            this.key = key;
            this.num = num;
        }

        /**
         * get key.
         *
         * @return key
         */
        public String getKey() {
            return key;
        }

        /**
         * get num.
         *
         * @return num
         */
        public Integer getNum() {
            return num;
        }
    }

    /**
     * <p>
     * create QueryOperatorNullCode
     * </p>
     * .
     *
     * @return QueryOperatorNullCode
     */
    public static BuilderExceptionCode createQueryOperatorNullCode() {
        return new BuilderExceptionCode(BuilderExceptionCodes.BUILDER10000.num, BuilderExceptionCodes.BUILDER10000.key);
    }

    /**
     * <p>
     * create NoConditionBehindCode
     * </p>
     * .
     *
     * @param operatorName operatorName
     * @return NoConditionBehindCode
     */
    public static BuilderExceptionCode createNoConditionBehindCode(java.lang.String operatorName) {
        return new BuilderExceptionCode(BuilderExceptionCodes.BUILDER10001.num, BuilderExceptionCodes.BUILDER10001.key,
            new Object[] { operatorName });
    }

    /**
     * <p>
     * create NextToSameConditionCode
     * </p>
     * .
     *
     * @param conditionTypeName conditionTypeName
     * @return NextToSameConditionCode
     */
    public static BuilderExceptionCode createNextToSameConditionCode(java.lang.String conditionTypeName) {
        return new BuilderExceptionCode(BuilderExceptionCodes.BUILDER10003.num, BuilderExceptionCodes.BUILDER10003.key,
            new Object[] { conditionTypeName });
    }

    /**
     * <p>
     * create IndexOutOffBoundsNameAliasSizeCode
     * </p>
     * .
     *
     * @param index index
     * @param size size
     * @return IndexOutOffBoundsNameAliasSizeCode
     */
    public static BuilderExceptionCode createIndexOutOffBoundsNameAliasSizeCode(java.lang.Integer index,
        java.lang.Integer size) {
        return new BuilderExceptionCode(BuilderExceptionCodes.BUILDER10004.num, BuilderExceptionCodes.BUILDER10004.key,
            new Object[] { index, size });
    }

    /**
     * <p>
     * create MulitipleNameCode
     * </p>
     * .
     *
     * @param name name
     * @return MulitipleNameCode
     */
    public static BuilderExceptionCode createMulitipleNameCode(java.lang.String name) {
        return new BuilderExceptionCode(BuilderExceptionCodes.BUILDER10005.num, BuilderExceptionCodes.BUILDER10005.key,
            new Object[] { name });
    }

}
