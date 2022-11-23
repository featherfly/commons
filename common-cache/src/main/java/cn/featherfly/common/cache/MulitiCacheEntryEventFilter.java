
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SimpleCacheEntryEventFilter.java
 * @Package cn.featherfly.common.cache
 * @Description: SimpleCacheEntryEventFilter
 * @author: zhongj
 * @date: 2022-11-23 14:45:23
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.cache;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListenerException;

/**
 * MulitiCacheEntryEventFilter.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class MulitiCacheEntryEventFilter<K, V> implements CacheEntryEventFilter<K, V> {

    private Set<CacheEntryEventFilter<K, V>> filters = new LinkedHashSet<>(0);

    /**
     * Adds the filter.
     *
     * @param filter the filter
     * @return the simple cache entry event filter
     */
    public MulitiCacheEntryEventFilter<K, V> addFilter(CacheEntryEventFilter<K, V> filter) {
        filters.add(filter);
        return this;
    }

    /**
     * Adds the listener.
     *
     * @param filters the filters
     * @return the simple cache entry event filter
     */
    public MulitiCacheEntryEventFilter<K, V> addFilter(
            @SuppressWarnings("unchecked") CacheEntryEventFilter<K, V>... filters) {
        for (CacheEntryEventFilter<K, V> filter : filters) {
            addFilter(filter);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(CacheEntryEvent<? extends K, ? extends V> event) throws CacheEntryListenerException {
        for (CacheEntryEventFilter<K, V> filter : filters) {
            if (filter.evaluate(event) == false) {
                return false;
            }
        }
        return true;
    }

}
