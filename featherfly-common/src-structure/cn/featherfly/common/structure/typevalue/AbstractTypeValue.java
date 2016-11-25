
package cn.featherfly.common.structure.typevalue;

import java.io.Serializable;

import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * IdObject
 * </p>
 * 
 * @author 钟冀
 */
public abstract class AbstractTypeValue<V extends Serializable> implements TypeValue<V> {

    private V value;
    
    private Class<V> type;
    
    /**
     * @param value value
     */
    public AbstractTypeValue(V value) {
        super();
//        AssertIllegalArgument.isNotEmpty(value, "value can not be null or empty");
        this.value = value;
        this.type = ClassUtils.getSuperClassGenricType(this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getValue() {
        return value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<V> getValueType() {
        return type;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (value != null) { 
            return value.toString();
        } else {
            return "";
        }
    }
}
