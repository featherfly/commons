
/*
 * All rights Reserved, Designed By zhongj
 * @Title: GenericType.java
 * @Package cn.featherfly.common.lang.reflect
 * @Description: GenericType
 * @author: zhongj
 * @date: 2023-03-22 14:39:22
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericType.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class GenericType<T> extends ClassType<T> {

    private List<GenericType<?>> genericTypes = new ArrayList<>(0);

    /**
     * Instantiates a new generic type.
     *
     * @param type the type
     */
    public GenericType(Class<T> type) {
        super(type);
    }

    /**
     * Adds the generic type.
     *
     * @param genericType the generic type
     * @return the generic type
     */
    public GenericType<T> addGenericType(GenericType<?> genericType) {
        genericTypes.add(genericType);
        return this;
    }

    /**
     * Adds the generic type.
     *
     * @param genericTypes the generic types
     * @return the generic type
     */
    public GenericType<T> addGenericType(GenericType<?>... genericTypes) {
        for (GenericType<?> genericType : genericTypes) {
            this.genericTypes.add(genericType);
        }
        return this;
    }

    /**
     * get genericTypes value
     *
     * @return genericTypes
     */
    public List<GenericType<?>> getGenericTypes() {
        return genericTypes;
    }

    public boolean isGeneric() {
        return genericTypes.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (isGeneric()) {
            return "{type=" + getType() + ", genericTypes=" + genericTypes + "}";
        } else {
            return "{" + getType() + "}";
        }
    }

}
