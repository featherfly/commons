package cn.featherfly.common.cache;

import java.util.concurrent.TimeUnit;

/**
 * The Class CacheConfig.
 *
 * @author zhongj
 */
public class CacheConfig {

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    //    // 缓存写入后的过期时间，如果大于0，则在写入后的指定时间后一定会过期
    //    private long ttl;
    //
    //    // 缓存读取后的过期时间，需要ttl为0才有效，不然在ttl过期后缓存就过期了，一般用于可续期缓存（如session）
    //    private long maxIdleTime;

    private int maxSize;

    private long expiry;

    private ExpiryPolicys expiryPolicy = ExpiryPolicys.ETERNAL;

    /**
     * Creates config object with <code>ttl = 0</code> and
     * <code>maxIdleTime = 0</code>.
     */
    public CacheConfig() {
    }

    /**
     * Creates config object.
     *
     * @param expiryPolicy the expiry policy
     * @param expiry       the expiry
     */
    public CacheConfig(ExpiryPolicys expiryPolicy, long expiry) {
        super();
        this.expiry = expiry;
    }

    /**
     * Instantiates a new cache config.
     *
     * @param expiryPolicy the expiry policy
     * @param expiry       the expiry
     * @param timeUnit     the time unit
     */
    public CacheConfig(ExpiryPolicys expiryPolicy, long expiry, TimeUnit timeUnit) {
        super();
        this.expiryPolicy = expiryPolicy;
        this.expiry = expiry;
        this.timeUnit = timeUnit;
    }

    /**
     * Instantiates a new cache config.
     *
     * @param expiryPolicy the expiry policy
     * @param expiry       the expiry
     * @param timeUnit     the time unit
     * @param maxSize      the max size
     */
    public CacheConfig(ExpiryPolicys expiryPolicy, long expiry, TimeUnit timeUnit, int maxSize) {
        super();
        this.expiryPolicy = expiryPolicy;
        this.timeUnit = timeUnit;
        this.expiry = expiry;
        this.maxSize = maxSize;
    }

    /**
     * Gets the max size.
     *
     * @return the max size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Set max size of map. Superfluous elements are evicted using LRU
     * algorithm.
     *
     * @param maxSize - max size If <code>0</code> the cache is unbounded
     *                (default).
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Gets the time unit.
     *
     * @return timeUnit
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * Sets the time unit.
     *
     * @param timeUnit timeUnit
     */
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * get expiry value.
     *
     * @return expiry
     */
    public long getExpiry() {
        return expiry;
    }

    /**
     * set expiry value.
     *
     * @param expiry expiry
     */
    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    /**
     * get expiryPolicy value.
     *
     * @return expiryPolicy
     */
    public ExpiryPolicys getExpiryPolicy() {
        return expiryPolicy;
    }

    /**
     * set expiryPolicy value.
     *
     * @param expiryPolicy expiryPolicy
     */
    public void setExpiryPolicy(ExpiryPolicys expiryPolicy) {
        this.expiryPolicy = expiryPolicy;
    }
}