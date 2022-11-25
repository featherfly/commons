
package cn.featherfly.common.lang.reflect;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * The Class ClassType.
 *
 * @author zhongj
 * @param <T> 类型
 */
public class ClassType<T> implements Type<T> {

    private Class<T> type;

    /**
     * Instantiates a new class type.
     *
     * @param type classType
     */
    public ClassType(Class<T> type) {
        AssertIllegalArgument.isNotNull(type, "Class<T> type");
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != ClassType.class) {
            return false;
        }
        return type.equals(((ClassType<?>) obj).type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName() {
        return type.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [type =" + getTypeName() + "]";
    }
}
