
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SimpleCacheEntryListener.java
 * @Package cn.featherfly.common.cache
 * @Description: SimpleCacheEntryListener
 * @author: zhongj
 * @date: 2022-11-22 17:50:22
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.cache;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListener;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;

/**
 * MulitiCacheEntryListener.
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class MulitiCacheEntryListener<K, V> implements CacheEntryCreatedListener<K, V>, CacheEntryUpdatedListener<K, V>,
        CacheEntryRemovedListener<K, V>, CacheEntryExpiredListener<K, V> {

    private Set<CacheEntryListener<K, V>> listeners = new LinkedHashSet<>(0);

    /**
     * Adds the listener.
     *
     * @param listener the listener
     * @return the simple cache entry listener
     */
    public MulitiCacheEntryListener<K, V> addListener(CacheEntryListener<K, V> listener) {
        listeners.add(listener);
        return this;
    }

    /**
     * Adds the listener.
     *
     * @param listeners the listeners
     * @return the simple cache entry listener
     */
    public MulitiCacheEntryListener<K, V> addListener(
            @SuppressWarnings("unchecked") CacheEntryListener<K, V>... listeners) {
        for (CacheEntryListener<K, V> listener : listeners) {
            addListener(listener);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        for (CacheEntryListener<K, V> listener : listeners) {
            if (listener instanceof CacheEntryCreatedListener) {
                ((CacheEntryCreatedListener<K, V>) listener).onCreated(events);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        for (CacheEntryListener<K, V> listener : listeners) {
            if (listener instanceof CacheEntryUpdatedListener) {
                ((CacheEntryUpdatedListener<K, V>) listener).onUpdated(events);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        for (CacheEntryListener<K, V> listener : listeners) {
            if (listener instanceof CacheEntryRemovedListener) {
                ((CacheEntryRemovedListener<K, V>) listener).onRemoved(events);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        for (CacheEntryListener<K, V> listener : listeners) {
            if (listener instanceof CacheEntryExpiredListener) {
                ((CacheEntryExpiredListener<K, V>) listener).onExpired(events);
            }
        }
    }
}
