
package cn.featherfly.common.validation;

import javax.annotation.Nonnull;

/**
 * <p>
 * V
 * </p>
 *
 * @author zhongj
 */
public class Name implements I {

    public Name() {
    }

    @Nonnull
    public Name(int a) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void name2(@Nonnull String name) {
        System.out.println("name2( " + name + " )");
    }
}