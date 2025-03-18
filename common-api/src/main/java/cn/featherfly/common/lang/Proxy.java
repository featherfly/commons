
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-03-18 13:51:18
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * Proxy.
 *
 * @author zhongj
 * @param <E> the element type
 */
public interface Proxy<E> {

    /**
     * Gets the proxy object.
     *
     * @return the proxy object
     */
    E getProxy();
}
