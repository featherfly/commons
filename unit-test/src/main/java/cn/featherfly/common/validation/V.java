
package cn.featherfly.common.validation;

import javax.annotation.Nonnull;

import cn.featherfly.common.validation.vo.User2;

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
        System.out.println("validation test not null -> " + s + ", " + s2);
    }

    public void noneNull(@Nonnull String s, @Nonnull User user, @Nonnull User2 user2) {
        if ("".equals(s)) {
            throw new java.lang.IllegalArgumentException("s is empty");
        }
        System.out.println("hi");
        System.out.println("validation test not null -> " + s + ", " + user + "," + user2);
    }

    public static void main(String[] args) {
        //        new V().notNull(null);
        if (args != null) {
            if (args.length >= 3) {
                new V().noneNull(args[0], null, null);
            } else if (args.length == 2) {
                new V().noneNull(args[0], args[1]);
            } else if (args.length == 1) {
                new V().noneNull(args[0], null);
            } else {
                new V().noneNull(null, null);
            }
        }
    }
}