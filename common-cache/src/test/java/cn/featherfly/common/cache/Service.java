
package cn.featherfly.common.cache;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;

/**
 * Service.
 *
 * @author zhongj
 */
@CacheDefaults(cacheName = Service.CACHE_NAME, cacheKeyGenerator = CacheKeyGeneratorImpl.class)
public class Service {

    public static final String CACHE_NAME = "ServiceCache";

    @CacheResult
    public String get(@CacheKey String key) {
        return key + ":" + System.currentTimeMillis();
    }

    @CachePut
    public int save(@CacheValue String value) {
        return 1;
    }
}
