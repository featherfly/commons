package cn.featherfly.common.spring.cache;

import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * MulitiUniqueKeyCache
 * </p>
 * .
 *
 * @author zhongj
 */
public class MulitiUniqueKeyCache implements Cache {

    /** The Constant DEFAULT_ID_KEY_PREFIX. */
    public static final String DEFAULT_ID_KEY_PREFIX = "MulitiUnique:id:";

    /** The Constant DEFAULT_ID_PROPERTY_NAME. */
    public static final String DEFAULT_ID_PROPERTY_NAME = "id";

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private int targetNotFoundIdValue = Integer.MIN_VALUE;

    private Cache targetCache;

    private Cache targetUniqueKeyCache;

    private Map<String, String> uniquePrefixPropertyMap;

    private String idKeyPrefix;

    private String idPropertyName;

    private String name;

    private Class<?> targetType;

    /**
     * Instantiates a new muliti unique key cache.
     *
     * @param name                    the name
     * @param targetCache             the target cache
     * @param targetUniqueKeyCache    the target unique key cache
     * @param uniquePrefixPropertyMap the unique prefix property map
     * @param targetType              the target type
     */
    public MulitiUniqueKeyCache(String name, Cache targetCache, Cache targetUniqueKeyCache,
            Map<String, String> uniquePrefixPropertyMap, Class<?> targetType) {
        this(name, targetCache, targetUniqueKeyCache, uniquePrefixPropertyMap, targetType, DEFAULT_ID_KEY_PREFIX,
                DEFAULT_ID_PROPERTY_NAME);
    }

    /**
     * Instantiates a new muliti unique key cache.
     *
     * @param name                    the name
     * @param targetCache             the target cache
     * @param targetUniqueKeyCache    the target unique key cache
     * @param uniquePrefixPropertyMap the unique prefix property map
     * @param targetType              the target type
     * @param idKeyPrefix             the id key prefix
     * @param idPropertyName          the id property name
     */
    public MulitiUniqueKeyCache(String name, Cache targetCache, Cache targetUniqueKeyCache,
            Map<String, String> uniquePrefixPropertyMap, Class<?> targetType, String idKeyPrefix,
            String idPropertyName) {
        this.name = name;
        this.uniquePrefixPropertyMap = uniquePrefixPropertyMap;
        this.targetCache = targetCache;
        this.targetUniqueKeyCache = targetUniqueKeyCache;
        this.idKeyPrefix = idKeyPrefix;
        this.idPropertyName = idPropertyName;
        this.targetType = targetType;
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
    public Object getNativeCache() {
        return targetCache.getNativeCache();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueWrapper get(Object key) {
        if (isUnique(key)) {
            ValueWrapper id = targetUniqueKeyCache.get(key);
            logger.debug("find id[{}] unique key [{}]", id, key);
            if (id == null || id.get() == null) {
                return null;
            }
            //            if (id == null) return null;
            if (id.get().equals(new Integer(targetNotFoundIdValue))) {
                return new SimpleValueWrapper(null);
            }
            return targetCache.get(id.get());
        } else {
            return targetCache.get(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (isUnique(key)) {
            Object id = targetUniqueKeyCache.get(key);
            logger.debug("find id[{}] unique key [{}]", id, key);
            if (id == null) {
                return null;
            }
            return (T) targetCache.get(id);
        } else {
            return (T) targetCache.get(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        if (isUnique(key)) {
            Object id = targetUniqueKeyCache.get(key, valueLoader);
            logger.debug("find id[{}] unique key [{}]", id, key);
            if (id == null) {
                return null;
            }
            return targetCache.get(id, valueLoader);
        } else {
            return targetCache.get(key, valueLoader);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(Object key, Object value) {
        assertSupportType(value);
        if (isUnique(key)) {
            if (value == null) {
                targetUniqueKeyCache.put(key, targetNotFoundIdValue);
            } else {
                Object id = BeanUtils.getProperty(value, idPropertyName);
                logger.debug("set id[{}] unique key [{}]", BeanUtils.getProperty(value, idPropertyName), key);
                putTarget(idKeyPrefix + id, value, id);
            }
        } else {
            putTarget(key, value, key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        assertSupportType(value);
        if (isUnique(key)) {
            if (value == null) {
                return targetUniqueKeyCache.putIfAbsent(key, targetNotFoundIdValue);
            } else {
                Object id = BeanUtils.getProperty(value, idPropertyName);
                logger.debug("set id[{}] unique key [{}]", id, key);
                return putTargetIfAbsend(idKeyPrefix + id, value, id);
            }
        } else {
            return putTargetIfAbsend(key, value, key);
        }
    }

    private void putTarget(Object key, Object value, Object id) {
        if (value != null) {
            uniquePrefixPropertyMap.forEach((k, v) -> {
                Object uniqueValue = BeanUtils.getProperty(value, v);
                if (Lang.isNotEmpty(uniqueValue)) {
                    targetUniqueKeyCache.put(k + uniqueValue, idKeyPrefix + id);
                }
            });
        }
        targetCache.put(key, value);
    }

    private ValueWrapper putTargetIfAbsend(Object key, Object value, Object id) {
        if (value != null) {
            uniquePrefixPropertyMap.forEach((k, v) -> {
                Object uniqueValue = BeanUtils.getProperty(value, v);
                if (Lang.isNotEmpty(uniqueValue)) {
                    targetUniqueKeyCache.putIfAbsent(k + uniqueValue, idKeyPrefix + id);
                }
            });
        }
        return targetCache.putIfAbsent(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void evict(Object key) {
        // 传入ID时应该把ID对应的全部唯一键都删除,传入唯一键时把唯一键对应的ID以及ID对应的其他唯一键全部删除
        final Object value;
        if (isUnique(key)) {
            String id = targetUniqueKeyCache.get(key, String.class);
            value = targetCache.get(id, targetType);
        } else {
            value = targetCache.get(key, targetType);
        }
        Object id = BeanUtils.getProperty(value, idPropertyName);
        targetCache.evict(idKeyPrefix + id);

        // 清除所有唯一键对应的ID缓存
        uniquePrefixPropertyMap.forEach((keyPrefix, propertyName) -> {
            Object uniqueValue = BeanUtils.getProperty(value, propertyName);
            targetUniqueKeyCache.evict(keyPrefix + uniqueValue);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        targetCache.clear();
        targetUniqueKeyCache.clear();
    }

    private boolean isUnique(Object key) {
        if (key != null && key instanceof String && uniquePrefixPropertyMap != null) {
            boolean result = false;
            for (String s : uniquePrefixPropertyMap.keySet()) {
                result = key.toString().startsWith(s);
                if (result) {
                    return result;
                }
            }
        }
        return false;
    }

    private void assertSupportType(Class<?> type) {
        if (type != null && !ClassUtils.isParent(targetType, type)) {
            throw new CacheException(String.format("只支持%s类型缓存，传入的类型%s不支持", targetType.getName(), type.getName()));
        }
    }

    private void assertSupportType(Object value) {
        if (value != null) {
            assertSupportType(value.getClass());
        }
    }

    /**
     * 返回targetCache.
     *
     * @return targetCache
     */
    public Cache getTargetCache() {
        return targetCache;
    }

    /**
     * 设置targetCache.
     *
     * @param targetCache targetCache
     */
    public void setTargetCache(Cache targetCache) {
        this.targetCache = targetCache;
    }

    /**
     * 返回targetUniqueKeyCache.
     *
     * @return targetUniqueKeyCache
     */
    public Cache getTargetUniqueKeyCache() {
        return targetUniqueKeyCache;
    }

    /**
     * 设置targetUniqueKeyCache.
     *
     * @param targetUniqueKeyCache targetUniqueKeyCache
     */
    public void setTargetUniqueKeyCache(Cache targetUniqueKeyCache) {
        this.targetUniqueKeyCache = targetUniqueKeyCache;
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
     * 设置name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回targetNotFoundIdValue.
     *
     * @return targetNotFoundIdValue
     */
    public int getTargetNotFoundIdValue() {
        return targetNotFoundIdValue;
    }

    /**
     * 设置targetNotFoundIdValue.
     *
     * @param targetNotFoundIdValue targetNotFoundIdValue
     */
    public void setTargetNotFoundIdValue(int targetNotFoundIdValue) {
        this.targetNotFoundIdValue = targetNotFoundIdValue;
    }
}
