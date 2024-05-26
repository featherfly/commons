
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

import java.util.HashMap;

/**
 * Params.
 *
 * @author zhongj
 */
public class Params extends HashMap<String, Object> {

    private static final long serialVersionUID = -5375023636015964291L;

    /**
     * Instantiates a new params.
     */
    public Params() {
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
        put(name, value);
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
