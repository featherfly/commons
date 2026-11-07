
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 16:36:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

/**
 * UrlAware.
 *
 * @author zhongj
 */
public interface UrlAware {

    UrlMethodAware get();

    UrlMethodAware head();

    UrlMethodAware delete();

    UrlFormMethodAware post();

    UrlFormMethodAware put();

    UrlFormMethodAware patch();
}
