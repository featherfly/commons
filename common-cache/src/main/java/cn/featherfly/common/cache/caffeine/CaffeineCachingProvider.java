
package cn.featherfly.common.cache.caffeine;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.OptionalFeature;
import javax.cache.spi.CachingProvider;

/**
 * <p>
 * CaffeineCachingProvider
 * </p>
 *
 * @author zhongj
 */
public class CaffeineCachingProvider implements CachingProvider {

    public static final URI DEFAULT_URI = URI.create("caffeine://default");

    private Properties defaultProperties = new Properties();

    private Map<String, CacheManager> managers = new HashMap<>();

    public static void regist() {
        System.setProperty(Caching.JAVAX_CACHE_CACHING_PROVIDER, CaffeineCachingProvider.class.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getDefaultURI() {
        return DEFAULT_URI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getDefaultProperties() {
        return defaultProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader, Properties properties) {
        String key = uri.toString() + "@" + classLoader.getClass().getName() + "@" + properties.toString();
        CacheManager manager = managers.get(key);
        if (manager == null) {
            manager = new CaffeineCacheManager(this, uri, classLoader, properties);
        }
        return manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
        return getCacheManager(uri, classLoader, getDefaultProperties());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManager getCacheManager() {
        return getCacheManager(getDefaultURI(), getDefaultClassLoader());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        close(getDefaultClassLoader());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close(ClassLoader classLoader) {
        close(getDefaultURI(), getDefaultClassLoader());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close(URI uri, ClassLoader classLoader) {
        managers.values().forEach(m -> {
            m.close();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupported(OptionalFeature optionalFeature) {
        return true;
    }

}
