
package cn.featherfly.common.lang.reflect;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * GenericClass
 * </p>
 * .
 *
 * @author zhongj
 * @param <T> 类型
 */
public class GenericClass<T> implements GenericType<T> {

    private Class<T> c;

    /**
     * Instantiates a new generic class.
     *
     * @param c classType
     */
    public GenericClass(Class<T> c) {
        AssertIllegalArgument.isNotNull(c, "Class<T> c");
        this.c = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return c.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != GenericClass.class) {
            return false;
        }
        return c.equals(((GenericClass<?>) obj).c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName() {
        return c.getName();
    }
}
