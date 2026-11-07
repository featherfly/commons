
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 17:11:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.io.Serializable;

import cn.featherfly.common.http.UploadFile;

/**
 * ParameterBuilder.
 *
 * @author zhongj
 */
public interface ParameterBuilder extends ArgumentsBuilder<ParameterBuilder> {

    /**
     * Sets the.
     *
     * @param name the name
     * @param files the files
     * @return the request parameter builder
     */
    default ParameterBuilder set(String name, UploadFile... files) {
        return set(name, (Serializable[]) files);
    }

    /**
     * Adds the.
     *
     * @param name the name
     * @param files the files
     * @return the request parameter builder
     */
    default ParameterBuilder add(String name, UploadFile... files) {
        return add(name, (Serializable[]) files);
    }
}
