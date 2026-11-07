
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 20:12:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.util.function.Consumer;

/**
 * FormParameterSetter.
 *
 * @author zhongj
 */
public interface FormParameterSetter extends ParameterSetterBase {
    /**
     * Parameter.
     *
     * @param body the body
     * @return the http request aware
     */
    HttpRequestAware parameter(Object body);

    /**
     * Body.
     *
     * @param body the body
     * @return the http request aware
     */
    default HttpRequestAware body(Object body) {
        return parameter(body);
    }

    /**
     * Parameter.
     *
     * @param parameterBuilder the parameter builder
     * @return the http request aware
     */
    HttpRequestAware parameter(Consumer<FormParameterBuilder> parameterBuilder);
}
