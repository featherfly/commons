
/*
 * All rights Reserved, Designed By zhongj
 * @Title: VariableValue.java
 * @Package cn.featherfly.common.lang.vt
 * @Description: VariableValue
 * @author: zhongj
 * @date: 2022-11-10 16:28:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.vt;

/**
 * VariableValue.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class VariableValue<T> implements ValueType<T> {

    private Class<T> type;

    private T value;

    /**
     * Instantiates a new variable value.
     *
     * @param type  the type
     * @param value the value
     */
    public VariableValue(Class<T> type, T value) {
        this.type = type;
        this.value = value;
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
    public T getValue() {
        return value;
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
    public int hashCode() {
        if (value != null) {
            return value.hashCode();
        } else {
            return getClass().hashCode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != VariableValue.class) {
            return false;
        }
        if (value == null) {
            return false;
        }
        return value.equals(((VariableValue<?>) obj).value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [type =" + getTypeName() + "]";
    }

}
