
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 20:11:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.util.Map;
import java.util.function.Consumer;

/**
 * HeaderSetter.
 *
 * @author zhongj
 */
public interface HeaderSetter<R> {

    /**
     * Header.
     *
     * @param headers the headers
     * @return the form url method header aware
     */
    R header(Map<String, String> headers);

    /**
     * Header.
     *
     * @param consumerHeader the consumer header
     * @return the url basic method header aware
     */
    R header(Consumer<HeaderBuilder> consumerHeader);
}
