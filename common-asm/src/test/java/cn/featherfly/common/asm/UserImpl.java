
package cn.featherfly.common.asm;

/**
 * UserImpl.
 *
 * @author zhongj
 */
public class UserImpl implements User {

    public String $$name() {
        return "yufei";
    }

    @Override
    public String name() {
        return "featherfly";
    }

    public int $$age() {
        return 18;
    }

    @Override
    public int age() {
        return 20;
    }
}
