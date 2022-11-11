
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
     * @param value the value
     */
    @SuppressWarnings("unchecked")
    public VariableValue(T value) {
        super();
        this.value = value;
        this.type = (Class<T>) value.getClass();
    }

    /**
     * Instantiates a new variable value.
     *
     * @param type  the type
     * @param value the value
     */
    public VariableValue(Class<T> type, T value) {
        super();
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

}
