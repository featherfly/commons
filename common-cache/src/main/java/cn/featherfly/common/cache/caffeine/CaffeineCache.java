
package cn.featherfly.common.cache.caffeine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

import com.github.benmanes.caffeine.cache.Cache;

/**
 * CaffeineCacheApiProxy.
 *
 * @author zhongj
 */
public class CaffeineCache<K, V> implements javax.cache.Cache<K, V> {

    private Cache<K, V> cache;

    private Set<CacheEntryListenerConfiguration<K, V>> cacheEntryListenerConfigurations = new HashSet<>();

    private String name;

    private CaffeineCacheManager cacheManager;

    private boolean close;

    private Map<Class<?>, Configuration<K, V>> configurations = new HashMap<>();

    /**
     * Instantiates a new caffeine impl.
     *
     * @param cache the cache
     * @param name  the name
     */
    public CaffeineCache(Cache<K, V> cache, String name, CaffeineCacheManager cacheManager) {
        super();
        this.cache = cache;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key) {
        return cache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        return cache.getAllPresent(keys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(K key) {
        return cache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getAndPut(K key, V value) {
        V v = get(key);
        put(key, value);
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        cache.putAll(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            put(key, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(K key) {
        boolean b = containsKey(key);
        if (b) {
            cache.invalidate(key);
        }
        return b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(K key, V oldValue) {
        V v = get(key);
        if (v != null && v.equals(oldValue)) {
            cache.invalidate(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getAndRemove(K key) {
        V v = get(key);
        if (v != null) {
            cache.invalidate(key);
        }
        return v;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        V v = get(key);
        if (v != null && v.equals(oldValue)) {
            cache.put(key, newValue);
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean replace(K key, V value) {
        V v = get(key);
        if (v != null) {
            cache.put(key, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getAndReplace(K key, V value) {
        V oldValue = get(key);
        if (oldValue != null) {
            cache.put(key, value);
        }
        return oldValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll(Set<? extends K> keys) {
        cache.invalidateAll(keys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll() {
        cache.invalidateAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        cache.cleanUp();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <C extends Configuration<K, V>> C getConfiguration(Class<C> clazz) {
        return (C) configurations.get(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadAll(Set<? extends K> keys, boolean replaceExistingValues, CompletionListener completionListener) {
        // 此方法用于远程加载，进程缓存无需实现
        completionListener.onCompletion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T invoke(K key, EntryProcessor<K, V, T> entryProcessor, Object... arguments)
            throws EntryProcessorException {
        //        entryProcessor.process(entry, arguments)
        // FIXME  未实现
        throw new RuntimeException("unsupported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> keys, EntryProcessor<K, V, T> entryProcessor,
            Object... arguments) {
        // FIXME  未实现
        throw new RuntimeException("unsupported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManager getCacheManager() {
        return cacheManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        close = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed() {
        return close;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(Class<T> clazz) {
        // YUFEI_TODO Auto-generated method stub
        System.out.println(this.getClass().getSimpleName() + " unwrap " + clazz.getName());
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        cacheEntryListenerConfigurations.add(cacheEntryListenerConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {
        cacheEntryListenerConfigurations.remove(cacheEntryListenerConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<javax.cache.Cache.Entry<K, V>> iterator() {
        List<javax.cache.Cache.Entry<K, V>> list = new ArrayList<>();
        for (java.util.Map.Entry<K, V> entry : cache.asMap().entrySet()) {
            Entry cacheEntry = new Entry(entry.getKey(), entry.getValue());
            list.add(cacheEntry);
        }
        return list.iterator();
    }

    class Entry implements javax.cache.Cache.Entry<K, V> {

        private K key;

        private V value;

        /**
         * @param key
         * @param value
         */
        public Entry(K key, V value) {
            super();
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public <T> T unwrap(Class<T> clazz) {
            // YUFEI_TODO Auto-generated method stub
            System.out.println(this.getClass().getSimpleName() + " unwrap " + clazz.getName());
            return null;
        }
    }

}
