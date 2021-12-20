package cn.featherfly.common.spring.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * MulitiUniqueKeyCacheManager.
 *
 * @author zhongj
 */
public class MulitiUniqueKeyCacheManager extends AbstractTransactionSupportingCacheManager {
    //        implements CacheManager,InitializingBean {

    private CacheManager cacheManager;

    private MulitiUniqueKeyCache cache;

    private String cacheName;

    private String idCacheName;

    private String uniqueKeyCacheName;

    private Map<String, String> uniquePrefixPropertyMap;

    private String idKeyPrefix;

    private String idPropertyName;

    private Class<?> targetType;

    /**
     * 返回cache.
     *
     * @return cache
     */
    public MulitiUniqueKeyCache getCache() {
        return cache;
    }

    /**
     * 设置cache.
     *
     * @param cache cache
     */
    public void setCache(MulitiUniqueKeyCache cache) {
        this.cache = cache;
    }

    /**
     * 返回cacheName.
     *
     * @return cacheName
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * 设置cacheName.
     *
     * @param cacheName cacheName
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 返回idCacheName.
     *
     * @return idCacheName
     */
    public String getIdCacheName() {
        return idCacheName;
    }

    /**
     * 设置idCacheName.
     *
     * @param idCacheName idCacheName
     */
    public void setIdCacheName(String idCacheName) {
        this.idCacheName = idCacheName;
    }

    /**
     * 返回uniqueKeyCacheName.
     *
     * @return uniqueKeyCacheName
     */
    public String getUniqueKeyCacheName() {
        return uniqueKeyCacheName;
    }

    /**
     * 设置uniqueKeyCacheName.
     *
     * @param uniqueKeyCacheName uniqueKeyCacheName
     */
    public void setUniqueKeyCacheName(String uniqueKeyCacheName) {
        this.uniqueKeyCacheName = uniqueKeyCacheName;
    }

    /**
     * 返回uniquePrefixPropertyMap.
     *
     * @return uniquePrefixPropertyMap
     */
    public Map<String, String> getUniquePrefixPropertyMap() {
        return uniquePrefixPropertyMap;
    }

    /**
     * 设置uniquePrefixPropertyMap.
     *
     * @param uniquePrefixPropertyMap uniquePrefixPropertyMap
     */
    public void setUniquePrefixPropertyMap(Map<String, String> uniquePrefixPropertyMap) {
        this.uniquePrefixPropertyMap = uniquePrefixPropertyMap;
    }

    /**
     * 返回idKeyPrefix.
     *
     * @return idKeyPrefix
     */
    public String getIdKeyPrefix() {
        return idKeyPrefix;
    }

    /**
     * 设置idKeyPrefix.
     *
     * @param idKeyPrefix idKeyPrefix
     */
    public void setIdKeyPrefix(String idKeyPrefix) {
        this.idKeyPrefix = idKeyPrefix;
    }

    /**
     * 返回idPropertyName.
     *
     * @return idPropertyName
     */
    public String getIdPropertyName() {
        return idPropertyName;
    }

    /**
     * 设置idPropertyName.
     *
     * @param idPropertyName idPropertyName
     */
    public void setIdPropertyName(String idPropertyName) {
        this.idPropertyName = idPropertyName;
    }

    /**
     * 返回targetType.
     *
     * @return targetType
     */
    public Class<?> getTargetType() {
        return targetType;
    }

    /**
     * 设置targetType.
     *
     * @param targetType targetType
     */
    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }

    /**
     * Gets the cache manager.
     *
     * @return the cache manager
     */
    public CacheManager getCacheManager() {
        return cacheManager;
    }

    /**
     * Sets the cache manager.
     *
     * @param cacheManager the new cache manager
     */
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cache getCache(String name) {
        if (cacheName.equals(name)) {
            return cache;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<? extends Cache> loadCaches() {
        AssertIllegalArgument.isNotEmpty(idCacheName, "idCacheName");
        AssertIllegalArgument.isNotEmpty(uniqueKeyCacheName, "idCacheName");

        Cache targetCache = cacheManager.getCache(idCacheName);
        Cache uniqueCache = cacheManager.getCache(uniqueKeyCacheName);

        AssertIllegalArgument.isNotEmpty(targetCache, "targetCache不存在");
        AssertIllegalArgument.isNotEmpty(uniqueCache, "uniqueCache不存在");

        cache = new MulitiUniqueKeyCache(cacheName, targetCache, uniqueCache, uniquePrefixPropertyMap, targetType,
                idKeyPrefix, idPropertyName);

        Set<Cache> caches = new HashSet<>();
        caches.add(cache);
        return caches;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Cache decorateCache(Cache cache) {
        return isTransactionAware() ? new TransactionAwareCacheDecorator(cache instanceof TransactionAwareCacheDecorator
                ? ((TransactionAwareCacheDecorator) cache).getTargetCache()
                : cache) : cache;
    }
}
