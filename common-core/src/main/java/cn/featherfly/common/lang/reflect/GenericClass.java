
package cn.featherfly.common.lang.reflect;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * GenericClass .
 *
 * @author zhongj
 * @param <T> 类型
 * @deprecated 后续删除，请使用{@link ClassType}替换
 */
@Deprecated
public class GenericClass<T> implements Type<T> {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [type =" + getTypeName() + "]";
    }

}
