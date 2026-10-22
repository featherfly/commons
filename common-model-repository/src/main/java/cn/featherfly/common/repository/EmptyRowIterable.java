
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-04-13 00:23:13
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

import java.util.Collections;
import java.util.Iterator;

/**
 * EmptyRowIterable.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class EmptyRowIterable<T> implements RowIterable<T> {

    /** The Constant INSTANCE. */
    public static final EmptyRowIterable<Object> EMPTY_ROWITERABLE = new EmptyRowIterable<>();

    /**
     * Empty row iterable.
     *
     * @param <T> the generic type
     * @return the row iterable
     */
    @SuppressWarnings("unchecked")
    public static <T> RowIterable<T> emptyRowIterable() {
        return (RowIterable<T>) EMPTY_ROWITERABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws RuntimeException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return Collections.emptyIterator();
    }
}
