
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 16:38:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

/**
 * UrlMethodAware.
 *
 * @author zhongj
 */
public interface UrlMethodAware extends HttpRequestAware, HeaderSetter<UrlMethodHeaderAware>, ParameterSetter {

}
