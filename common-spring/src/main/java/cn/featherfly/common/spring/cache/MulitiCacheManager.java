package cn.featherfly.common.spring.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;

/**
 * MulitiCacheManager.
 *
 * @author zhongj
 */
public class MulitiCacheManager extends AbstractTransactionSupportingCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MulitiCacheManager.class);

    private List<CacheManager> cacheManagers;

    public MulitiCacheManager() {
        super();
    }

    /**
     * Getter for property 'cacheManagers'.
     *
     * @return Value for property 'cacheManagers'.
     */
    public List<CacheManager> getCacheManagers() {
        return cacheManagers;
    }

    /**
     * Setter for property 'cacheManagers'.
     *
     * @param cacheManagers Value to set for property 'cacheManagers'.
     */
    public void setCacheManagers(List<CacheManager> cacheManagers) {
        if (cacheManagers == null) {
            cacheManagers = new ArrayList<>();
        }
        this.cacheManagers = cacheManagers;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        Map<String, Cache> cacheMap = new HashMap<>();
        for (CacheManager cacheManager : cacheManagers) {
            for (String name : cacheManager.getCacheNames()) {
                Cache cache = cacheManager.getCache(name);
                if (cacheMap.containsKey(name)) {
                    throw new CacheException(String.format("muliti cache named %s, cachs[%s, %s]", name,
                            cacheMap.get(name).getClass().getName(), cache.getClass().getName()));
                }
                if (cache != null) {
                    cacheMap.put(name, cache);
                    LOGGER.debug("cache named {} in cacheManager[{}]", name, cacheManager.getClass().getName());
                }
            }
        }
        return cacheMap.values();
    }

    @Override
    protected Cache decorateCache(Cache cache) {
        return isTransactionAware() ? new TransactionAwareCacheDecorator(cache instanceof TransactionAwareCacheDecorator
                ? ((TransactionAwareCacheDecorator) cache).getTargetCache()
                : cache) : cache;
    }
}
