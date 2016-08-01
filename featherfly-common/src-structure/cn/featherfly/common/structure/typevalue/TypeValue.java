package cn.featherfly.common.structure.typevalue;

import java.io.Serializable;

/**
 * <p>
 * 强类型值
 * </p>
 * 
 * @author 钟冀
 */
public interface TypeValue<V extends Serializable> {

    /**
     * 返回value
     * @return value
     */
    public abstract V getValue();
}