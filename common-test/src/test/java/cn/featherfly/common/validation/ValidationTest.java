
package cn.featherfly.common.validation;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.testng.annotations.Test;

/**
 * <p>
 * ValidationTest
 * </p>
 * .
 *
 * @author zhongj
 */
public class ValidationTest {

    /**
     * Not null.
     *
     * @param s the s
     */
    public void notNull(@NotNull(message = "not null") String s) {
        System.out.println("validation test not null -> " + s);
    }

    /**
     * Not null.
     *
     * @param s the s
     */
    public void nonNull(@Nonnull String s) {
        System.out.println("validation test nonnull -> " + s);
    }

    /**
     * Test null.
     */
    @Test
    public void testNotNull() {
        notNull(null);
    }

    /**
     * Test null.
     */
    @Test
    public void testNonnull() {
        nonNull(null);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        new ValidationTest().notNull(null);
    }
}
