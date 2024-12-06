
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-19 20:01:19
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import cn.featherfly.common.exception.BaseException;

/**
 * AutocloseIterable.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface AutoCloseableIterable<T> extends Iterable<T>, AutoCloseable {

    /**
     * {@inheritDoc}
     */
    @Override
    void close() throws BaseException;
}
