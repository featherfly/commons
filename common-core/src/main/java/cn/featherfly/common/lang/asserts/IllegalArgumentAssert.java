
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
     * Instantiates a new illegal argument assert.
     */
    public IllegalArgumentAssert() {
        super(IllegalArgumentException.class, IllegalArgumentException::new);
    }
}
