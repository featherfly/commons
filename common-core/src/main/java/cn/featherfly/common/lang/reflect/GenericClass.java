
package cn.featherfly.common.lang.reflect;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * GenericClass
 * </p>
 * 
 * @param <T> 类型
 * @author zhongj
 */
public class GenericClass<T> implements GenericType<T> {

    private Class<T> c;

    /**
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
}
