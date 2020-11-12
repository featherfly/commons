
package cn.featherfly.common.validation;

import javax.annotation.Nonnull;

/**
 * <p>
 * V
 * </p>
 *
 * @author zhongj
 */
public class V {

    public V() {
    }

    @Nonnull
    public V(int a) {
    }

    //    public void notNull(@NotNull(message = "not null") String s) {
    //        if ("".equals(s)) {
    //            throw new java.lang.IllegalArgumentException("s is empty");
    //        }
    //        System.out.println("hi");
    //        {
    //            System.out.println("validation test not null -> " + s);
    //        }
    //    }

    public void noneNull(@Nonnull String s) {
        if ("".equals(s)) {
            throw new java.lang.IllegalArgumentException("s is empty");
        }
        System.out.println("hi");
        {
            System.out.println("validation test not null -> " + s);
        }
    }

    public void noneNull(@Nonnull String s, @Nonnull String s2) {
        if ("".equals(s)) {
            throw new java.lang.IllegalArgumentException("s is empty");
        }
        System.out.println("hi");
        {
            System.out.println("validation test not null -> " + s);
        }
    }

    public static void main(String[] args) {
        //        new V().notNull(null);
        new V().noneNull(null, null);
    }
}