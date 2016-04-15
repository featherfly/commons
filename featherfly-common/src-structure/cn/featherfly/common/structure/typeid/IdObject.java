
package cn.featherfly.common.structure.typeid;

import java.io.Serializable;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * IdObject
 * </p>
 * 
 * @author 钟冀
 */
public abstract class IdObject<V extends Serializable> {

    private V value;
    
    /**
     * @param value
     */
    public IdObject(V value) {
        super();
        AssertIllegalArgument.isNotEmpty(value, "value can not be null or empty");
        this.value = value;
    }

    /**
     * 返回value
     * @return value
     */
    public V getValue() {
        return value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
