
package cn.featherfly.common.cache;

import java.net.URI;
import java.util.Map;
import java.util.Properties;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.Factory;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.expiry.EternalExpiryPolicy;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.expiry.ModifiedExpiryPolicy;
import javax.cache.expiry.TouchedExpiryPolicy;
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
            //            if ("com.github.benmanes.caffeine.jcache.CacheManagerImpl".equals(cacheManager.getClass().getName())) {
            //                try {
            //                    @SuppressWarnings("unchecked")
            //                    CompleteConfiguration<Object, Object> cc = (CompleteConfiguration<Object, Object>) Class
            //                            .forName("com.github.benmanes.caffeine.jcache.configuration.CaffeineConfiguration")
            //                            .getConstructor(CompleteConfiguration.class).newInstance(configuration);
            //                    if (config.getTTL() > 0) {
            //                        setValue(cc, "setExpireAfterWrite",
            //                                OptionalLong.of(config.getTimeUnit().toNanos(config.getTTL())));
            //                    }
            //                    if (config.getMaxIdleTime() > 0) {
            //                        setValue(cc, "setExpireAfterAccess",
            //                                OptionalLong.of(config.getTimeUnit().toNanos(config.getMaxIdleTime())));
            //                    }
            //                    if (config.getMaxSize() > 0) {
            //                        setValue(cc, "setMaximumSize", OptionalLong.of(config.getMaxSize()));
            //                    }
            //                    cacheManager.createCache(entry.getKey(), cc);
            //                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            //                        | InvocationTargetException | NoSuchMethodException | SecurityException
            //                        | ClassNotFoundException e) {
            //                    // TODO 后续加入自定义异常
            //                    throw new CacheException(e);
            //                }
            //            } else {

            Duration expriy = new Duration(config.getTimeUnit(), config.getExpiry());
            configuration.setExpiryPolicyFactory(factoryOf(expriy, config.getExpiryPolicy()));
            cacheManager.createCache(entry.getKey(), configuration);
            //            }
        }
    }

    /**
     * Factory of.
     *
     * @param expiryForCreation the expiry
     * @return the factory
     */
    private static Factory<ExpiryPolicy> factoryOf(Duration expiry, ExpiryPolicys policy) {
        switch (policy) {
            case ETERNAL:
                return new FactoryBuilder.SingletonFactory<>(new EternalExpiryPolicy());
            case CREATED:
                return new FactoryBuilder.SingletonFactory<>(new CreatedExpiryPolicy(expiry));
            case ACCESSED:
                return new FactoryBuilder.SingletonFactory<>(new AccessedExpiryPolicy(expiry));
            case MODIFIED:
                return new FactoryBuilder.SingletonFactory<>(new ModifiedExpiryPolicy(expiry));
            case TOUCHED:
                return new FactoryBuilder.SingletonFactory<>(new TouchedExpiryPolicy(expiry));
            default:
                return new FactoryBuilder.SingletonFactory<>(new EternalExpiryPolicy());
        }
    }

    //    private void setValue(CompleteConfiguration<Object, Object> cc, String setMethodName, Object value)
    //            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
    //            SecurityException {
    //        cc.getClass().getMethod(setMethodName, OptionalLong.class).invoke(cc, value);
    //    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
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
