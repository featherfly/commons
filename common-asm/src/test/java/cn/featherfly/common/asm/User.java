
package cn.featherfly.common.asm;

/**
 * User.
 *
 * @author zhongj
 */
public interface User {

    default String name() {
        return "yufei";
    }

    default U u() {
        return new U();
    }

    default byte age1() {
        return 18;
    }

    default short age2() {
        return 18;
    }

    default int age() {
        return 18;
    }

    default long age4() {
        return 18l;
    }

    default double d() {
        return 18d;
    }

    default float f() {
        return 18;
    }
}
