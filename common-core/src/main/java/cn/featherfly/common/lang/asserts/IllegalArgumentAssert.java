
package cn.featherfly.common.lang.asserts;

/**
 * IllegalArgumentAssert.
 *
 * @since 1.7
 * @version 1.7
 * @author zhongj
 */
public class IllegalArgumentAssert extends LocalizedAssert<IllegalArgumentException> {

    /**
     */
    public IllegalArgumentAssert() {
        super(IllegalArgumentException.class);
    }

}
