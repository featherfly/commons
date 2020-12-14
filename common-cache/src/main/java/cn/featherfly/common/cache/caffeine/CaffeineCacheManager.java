
package cn.featherfly.common.cache.caffeine;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.spi.CachingProvider;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * <p>
 * CaffeineCacheManager
 * </p>
 * .
 *
 * @author zhongj
 */
public class CaffeineCacheManager implements CacheManager {

    private CaffeineCachingProvider cachingProvider;

    private URI uri;

    private ClassLoader classLoader;

    private Properties properties;

    private Map<String, Cache<?, ?>> caches = new HashMap<>();
    private Map<String, CompleteConfiguration<?, ?>> configurations = new HashMap<>();

    private boolean close;

    //    /**
    //     * Instantiates a new caffeine cache manager.
    //     *
    //     * @param cachingProvider the caching provider
    //     */
    //    public CaffeineCacheManager(CaffeineCachingProvider cachingProvider) {
    //        this(cachingProvider, DEFAULT_URI, Thread.currentThread().getContextClassLoader());
    //    }
    //
    //    /**
    //     * Instantiates a new caffeine cache manager.
    //     *
    //     * @param cachingProvider the caching provider
    //     */
    //    public CaffeineCacheManager(CaffeineCachingProvider cachingProvider, URI uri, ClassLoader classLoader) {
    //        this(cachingProvider, uri, classLoader, null);
    //    }

    /**
     * Instantiates a new caffeine cache manager.
     *
     * @param cachingProvider the caching provider
     */
    public CaffeineCacheManager(CaffeineCachingProvider cachingProvider, URI uri, ClassLoader classLoader,
            Properties properties) {
        super();
        this.cachingProvider = cachingProvider;
        this.uri = uri;
        this.classLoader = classLoader;
        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachingProvider getCachingProvider() {
        return cachingProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getURI() {
        return uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties() {
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration)
            throws IllegalArgumentException {
        if (configuration instanceof CompleteConfiguration) {
            @SuppressWarnings("unchecked")
            CompleteConfiguration<K, V> mc = (CompleteConfiguration<K, V>) configuration;
            configurations.put(cacheName, mc);

            ExpiryPolicy expiryPolicy = mc.getExpiryPolicyFactory().create();

            Duration expiryForCreation = expiryPolicy.getExpiryForCreation();
            Duration expiryForAccess = expiryPolicy.getExpiryForAccess();

            CaffeineCache<K, V> cache = new CaffeineCache<>(Caffeine.newBuilder().recordStats()
                    .expireAfterWrite(expiryForCreation.getDurationAmount(), expiryForCreation.getTimeUnit())
                    .maximumSize(1)
                    .expireAfterAccess(expiryForAccess.getDurationAmount(), expiryForAccess.getTimeUnit()).build(),
                    cacheName, this);

            caches.put(cacheName, cache);
            return cache;
        } else {
            throw new IllegalArgumentException("configuration[" + configuration.getClass().getName()
                    + "] is not instanceof " + CompleteConfiguration.class.getName());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType) {
        //        String cacheKey = cacheName + "@" + keyType.getName() + "@" + valueType.getName();
        //        return (Cache<K, V>) caches.get(cacheKey);
        return getCache(cacheName);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        return (Cache<K, V>) caches.get(cacheName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getCacheNames() {
        return caches.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyCache(String cacheName) {
        Cache<?, ?> cache = caches.remove(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableManagement(String cacheName, boolean enabled) {
        // FIXME unsupported
        throw new RuntimeException("unsupported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableStatistics(String cacheName, boolean enabled) {
        // YUFEI_TODO Auto-generated method stub
        throw new RuntimeException("unsupported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        caches.values().forEach(c -> c.close());
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
        // FIXME unsupported
        System.out.println(this.getClass().getSimpleName() + " unwrap " + clazz.getName());
        throw new RuntimeException("unsupported");
    }

}
