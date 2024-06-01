
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Params.java
 * @Package cn.featherfly.common.repository
 * @Description: Params
 * @author: zhongj
 * @date: 2023-03-17 13:52:17
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.io.Serializable;
import java.util.Map;

import cn.featherfly.common.structure.ChainMapImpl;

/**
 * Params.
 *
 * @author zhongj
 */
public class Params extends ChainMapImpl<String, Serializable> {

    private static final long serialVersionUID = -5375023636015964291L;

    /**
     * Instantiates a new params.
     */
    public Params() {
        super();
    }

    /**
     * Instantiates a new params.
     *
     * @param map the map
     */
    public Params(Map<String, Serializable> map) {
        super(map);
    }

    /**
     * Sets the param.
     *
     * @param params the params
     * @return the params
     */
    public static final Params setParam(Map<String, ?> params) {
        return new Params().set(params);
    }

    /**
     * Sets the param.
     *
     * @param name the name
     * @param value the value
     * @return the params
     */
    public static final Params setParam(String name, Object value) {
        return new Params().set(name, value);
    }

    /**
     * Sets the.
     *
     * @param name the name
     * @param value the value
     * @return the params
     */
    public Params set(String name, Object value) {
        if (value != null && !(value instanceof Serializable)) {
            throw new IllegalArgumentException("param value type must implements " + Serializable.class.getName());
        }
        return set(name, (Serializable) value);
    }

    /**
     * Sets the.
     *
     * @param name the name
     * @param value the value
     * @return the params
     */
    public Params set(String name, Serializable value) {
        put(name, value);
        return this;
    }

    /**
     * Sets the.
     *
     * @param params the params
     * @return the params
     */
    public Params set(Map<String, ?> params) {
        if (params == null) {
            return this;
        }
        for (Entry<String, ?> entrySet : params.entrySet()) {
            set(entrySet.getKey(), entrySet.getValue());
        }
        return this;
    }

    /**
     * The Enum ParamType.
     */
    public enum ParamType {

        /** 没有参数. */
        NONE
    }
}
