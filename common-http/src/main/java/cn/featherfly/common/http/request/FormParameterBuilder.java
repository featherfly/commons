
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
 * FormParameterBuilder.
 *
 * @author zhongj
 */
public interface FormParameterBuilder extends ArgumentsBuilder<FormParameterBuilder> {

    /**
     * Sets the.
     *
     * @param name the name
     * @param files the files
     * @return the request parameter builder
     */
    default FormParameterBuilder set(String name, UploadFile... files) {
        return set(name, (Serializable[]) files);
    }

    /**
     * Adds the.
     *
     * @param name the name
     * @param files the files
     * @return the request parameter builder
     */
    default FormParameterBuilder add(String name, UploadFile... files) {
        return add(name, (Serializable[]) files);
    }

    /**
     * Body.
     *
     * @param body the body
     * @return the http request
     */
    HttpRequestAware body(Object body);
}
