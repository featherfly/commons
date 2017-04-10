package cn.featherfly.common.structure.typevalue;

import java.io.Serializable;

/**
 * <p>
 * 强类型值
 * </p>
 * 
 * @author 钟冀
 */
public interface TypeValue<V extends Serializable> extends Serializable{

    /**
     * 返回value
     * @return value
     */
    V getValue();
    /**
     * 返回value.class
     * @return value.class
     */
    Class<V> getValueType();
}