
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 16:30:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

/**
 * MethodAwareHttpBuilder.
 *
 * @author zhongj
 * @param <R> the generic type
 */
public interface MethodAwareHttpBuilder<R> {

    /**
     * Url.
     *
     * @param url the url
     * @return the r
     */
    R url(String url);
}
