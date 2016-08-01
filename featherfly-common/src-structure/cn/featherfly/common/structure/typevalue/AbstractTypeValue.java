
package cn.featherfly.common.structure.typevalue;

import java.io.Serializable;

/**
 * <p>
 * IdObject
 * </p>
 * 
 * @author 钟冀
 */
public abstract class AbstractTypeValue<V extends Serializable> implements TypeValue<V> {

    private V value;
    
    /**
     * @param value
     */
    public AbstractTypeValue(V value) {
        super();
//        AssertIllegalArgument.isNotEmpty(value, "value can not be null or empty");
        this.value = value;
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
    public String toString() {
        if (value != null) { 
            return value.toString();
        } else {
            return "";
        }
    }
}
