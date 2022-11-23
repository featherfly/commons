
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SimpleCacheEntryListenerConfiguration.java
 * @Package cn.featherfly.common.cache
 * @Description: SimpleCacheEntryListenerConfiguration
 * @author: zhongj
 * @date: 2022-11-22 17:49:22
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.cache;

import javax.cache.CacheException;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListener;

/**
 * SimpleCacheEntryListenerConfiguration.
 * <ul>
 * <li>isSynchronous 为true的作用是保证filter运行完以后再运行listener
 * </ul>
 *
 * @author zhongj
 * @param <K> the key type
 * @param <V> the value type
 */
public class MulitiCacheEntryListenerConfiguration<K, V> extends MutableCacheEntryListenerConfiguration<K, V> {

    private static final long serialVersionUID = 8396551121944202596L;

    private MulitiCacheEntryListener<K, V> listener;

    private MulitiCacheEntryEventFilter<K, V> filter;

    /**
     * Instantiates a new simple cache entry listener configuration.
     */
    public MulitiCacheEntryListenerConfiguration() {
        this(false);
    }

    /**
     * Instantiates a new simple cache entry listener configuration.
     *
     * @param isSynchronous the is synchronous
     */
    public MulitiCacheEntryListenerConfiguration(boolean isSynchronous) {
        this(false, isSynchronous);
    }

    /**
     * Instantiates a new simple cache entry listener configuration.
     *
     * @param isOldValueRequired the is old value required
     * @param isSynchronous      the is synchronous
     */
    public MulitiCacheEntryListenerConfiguration(boolean isOldValueRequired, boolean isSynchronous) {
        this(new MulitiCacheEntryListener<>(), new MulitiCacheEntryEventFilter<>(), isOldValueRequired, isSynchronous);
    }

    private MulitiCacheEntryListenerConfiguration(MulitiCacheEntryListener<K, V> listener,
            MulitiCacheEntryEventFilter<K, V> filter, boolean isOldValueRequired, boolean isSynchronous) {
        super(() -> listener, () -> filter, isOldValueRequired, isSynchronous);
        this.listener = listener;
        this.filter = filter;
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     * @return the simple cache entry listener
     * @see cn.featherfly.common.cache.MulitiCacheEntryListener#addListener(javax.cache.event.CacheEntryListener)
     */
    public MulitiCacheEntryListenerConfiguration<K, V> addListener(CacheEntryListener<K, V> listener) {
        this.listener.addListener(listener);
        return this;
    }

    /**
     * Adds the listener.
     *
     * @param listeners the listeners
     * @return the simple cache entry listener
     * @see cn.featherfly.common.cache.MulitiCacheEntryListener#addListener(javax.cache.event.CacheEntryListener[])
     */
    public MulitiCacheEntryListenerConfiguration<K, V> addListener(
            @SuppressWarnings("unchecked") CacheEntryListener<K, V>... listeners) {
        this.listener.addListener(listeners);
        return this;
    }

    /**
     * Adds the filter.
     *
     * @param filter the filter
     * @return the simple cache entry event filter
     * @see cn.featherfly.common.cache.MulitiCacheEntryEventFilter#addFilter(javax.cache.event.CacheEntryEventFilter)
     */
    public MulitiCacheEntryListenerConfiguration<K, V> addFilter(CacheEntryEventFilter<K, V> filter) {
        this.filter.addFilter(filter);
        return this;
    }

    /**
     * Adds the filter.
     *
     * @param filters the filters
     * @return the simple cache entry event filter
     * @see cn.featherfly.common.cache.MulitiCacheEntryEventFilter#addFilter(javax.cache.event.CacheEntryEventFilter[])
     */
    public MulitiCacheEntryListenerConfiguration<K, V> addFilter(
            @SuppressWarnings("unchecked") CacheEntryEventFilter<K, V>... filters) {
        filter.addFilter(filters);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableCacheEntryListenerConfiguration<K, V> setCacheEntryListenerFactory(
            Factory<? extends CacheEntryListener<? super K, ? super V>> listenerFactory) {
        throw new CacheException("use addListener method to add listener");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MutableCacheEntryListenerConfiguration<K, V> setCacheEntryEventFilterFactory(
            Factory<? extends CacheEntryEventFilter<? super K, ? super V>> filterFactory) {
        throw new CacheException("use addFilter method to add filter");
    }

}
