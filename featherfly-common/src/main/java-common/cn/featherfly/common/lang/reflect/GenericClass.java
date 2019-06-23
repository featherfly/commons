
package cn.featherfly.common.lang.reflect;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * GenericClass
 * </p>
 * @param <T> 类型
 * @author 钟冀
 */
public class GenericClass<T> implements GenericType<T>{

    private Class<T> c;

    /**
     * @param c classType
     */
    public GenericClass(Class<T> c) {
        AssertIllegalArgument.isNotNull(c, "param class");
        this.c = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return c;
    }
}
