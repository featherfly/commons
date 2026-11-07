
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 20:09:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.io.Serializable;
import java.util.Map;

/**
 * ParameterSetterBase.
 *
 * @author zhongj
 */
public interface ParameterSetterBase {
    /**
     * Parameter.
     *
     * @param parameters the parameters
     * @return the http request aware
     */
    HttpRequestAware parameter(Map<String, Serializable> parameters);

    //    /**
    //     * Parameter.
    //     *
    //     * @param body the body
    //     * @return the http request aware
    //     */
    //    HttpRequestAware parameter(Object body);
    //
    //    /**
    //     * Parameter.
    //     *
    //     * @param parameterBuilder the parameter builder
    //     * @return the http request aware
    //     */
    //    HttpRequestAware parameter(Consumer<FormParameterBuilder> parameterBuilder);
}
