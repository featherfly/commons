
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-11-07 17:02:07
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.http.request;

import java.util.stream.StreamSupport;

/**
 * HeaderBuilder.
 *
 * @author zhongj
 */
public interface HeaderBuilder {

    //    HeaderBuilder clear(String name);

    //    HeaderBuilder remove(String name);

    /**
     * Sets the.
     *
     * @param name the name
     * @param values the values
     * @return the HeaderBuilder
     */
    HeaderBuilder set(String name, String... values);

    /**
     * Sets the.
     *
     * @param name the name
     * @param values the values
     * @return the HeaderBuilder
     */
    default HeaderBuilder set(String name, Iterable<String> values) {
        if (values == null) {
            return this;
        }
        return set(name, StreamSupport.stream(values.spliterator(), false).toArray(String[]::new));
    }

    /**
     * Adds the.
     *
     * @param name the name
     * @param values the values
     * @return the HeaderBuilder
     */
    HeaderBuilder add(String name, String... values);

    /**
     * Adds the.
     *
     * @param name the name
     * @param values the values
     * @return the HeaderBuilder
     */
    default HeaderBuilder add(String name, Iterable<String> values) {
        if (values == null) {
            return this;
        }
        for (String value : values) {
            add(name, value);
        }
        return this;
    }
}
