
package cn.featherfly.common.cache;

import java.net.URI;
import java.util.Map;
import java.util.Properties;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * CacheManager.
 *
 * @author zhongj
 */
public class CacheManager implements javax.cache.CacheManager {

    private javax.cache.CacheManager cacheManager;

    /**
     * Instantiates a new cache manager.
     *
     * @param cacheConfigs the cache configs
     */
    public CacheManager(Map<String, CacheConfig> cacheConfigs) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        cacheManager = cachingProvider.getCacheManager();
        loadCaches(cacheConfigs);
    }

    private void loadCaches(Map<String, CacheConfig> cacheConfigs) {
        for (java.util.Map.Entry<String, CacheConfig> entry : cacheConfigs.entrySet()) {
            MutableConfiguration<Object, Object> configuration = new MutableConfiguration<>();
            CacheConfig config = entry.getValue();

            Duration ttl = Duration.ETERNAL;
            if (config.getTTL() > 0) {
                ttl = new Duration(config.getTimeUnit(), config.getTTL());
            }
            Duration idleTime = null;
            if (config.getMaxIdleTime() > 0) {
                idleTime = new Duration(config.getTimeUnit(), config.getMaxIdleTime());
            }
            configuration.setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(ttl, idleTime));
            cacheManager.createCache(entry.getKey(), configuration);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        if (cacheManager != null) {
            return cacheManager.getCache(cacheName);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CachingProvider getCachingProvider() {
        return cacheManager.getCachingProvider();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getURI() {
        return cacheManager.getURI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassLoader getClassLoader() {
        return cacheManager.getClassLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties() {
        return cacheManager.getProperties();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration)
            throws IllegalArgumentException {
        return cacheManager.createCache(cacheName, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType) {
        return cacheManager.getCache(cacheName, keyType, valueType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyCache(String cacheName) {
        cacheManager.destroyCache(cacheName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableManagement(String cacheName, boolean enabled) {
        cacheManager.enableManagement(cacheName, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableStatistics(String cacheName, boolean enabled) {
        cacheManager.enableStatistics(cacheName, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        cacheManager.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClosed() {
        return cacheManager.isClosed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T unwrap(Class<T> clazz) {
        return cacheManager.unwrap(clazz);
    }

}
