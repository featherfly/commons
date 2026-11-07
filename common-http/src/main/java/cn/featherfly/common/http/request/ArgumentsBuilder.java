
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 17:42:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.io.Serializable;
import java.util.stream.StreamSupport;

/**
 * ArgumentsBuilder.
 *
 * @author zhongj
 * @param <B> the generic type
 */
public interface ArgumentsBuilder<B extends ArgumentsBuilder<B>> extends HttpRequestAware {

    /**
     * Sets the.
     *
     * @param name the name
     * @param values the values
     * @return the b
     */
    B set(String name, Serializable... values);

    /**
     * Sets the.
     *
     * @param name the name
     * @param values the values
     * @return the b
     */
    @SuppressWarnings("unchecked")
    default B set(String name, Iterable<Serializable> values) {
        if (values == null) {
            return (B) this;
        }
        return set(name, StreamSupport.stream(values.spliterator(), false).toArray(Serializable[]::new));
    }

    /**
     * Adds the.
     *
     * @param name the name
     * @param values the values
     * @return the b
     */
    B add(String name, Serializable... values);

    /**
     * Adds the.
     *
     * @param name the name
     * @param values the values
     * @return the b
     */
    @SuppressWarnings("unchecked")
    default B add(String name, Iterable<Serializable> values) {
        if (values == null) {
            return (B) this;
        }
        for (Serializable value : values) {
            add(name, value);
        }
        return (B) this;
    }
}
