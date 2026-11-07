
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 17:18:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

/**
 * HttpRequestAware.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface HttpRequestAware {

    /**
     * Gets the http request.
     *
     * @return the http request
     */
    HttpRequest getHttpRequest();
}
