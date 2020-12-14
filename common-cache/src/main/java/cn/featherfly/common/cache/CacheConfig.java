package cn.featherfly.common.cache;

import java.util.concurrent.TimeUnit;

/**
 * The Class CacheConfig.
 *
 * @author zhongj
 */
public class CacheConfig {

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    private long ttl;

    private long maxIdleTime;

    private int maxSize;

    /**
     * Creates config object with <code>ttl = 0</code> and
     * <code>maxIdleTime = 0</code>.
     */
    public CacheConfig() {
    }

    /**
     * Creates config object.
     *
     * @param ttl - time to live for key\value entry in milliseconds. If
     *            <code>0</code> then time to live doesn't affect entry
     *            expiration.
     */
    public CacheConfig(long ttl) {
        super();
        this.ttl = ttl;
    }

    /**
     * Creates config object.
     *
     * @param ttl         - time to live for key\value entry in milliseconds. If
     *                    <code>0</code> then time to live doesn't affect entry
     *                    expiration.
     * @param maxIdleTime - max idle time for key\value entry in milliseconds.
     *                    <p>
     *                    if <code>maxIdleTime</code> and <code>ttl</code>
     *                    params are equal to <code>0</code> then entry stores
     *                    infinitely.
     */
    public CacheConfig(long ttl, long maxIdleTime) {
        super();
        this.ttl = ttl;
        this.maxIdleTime = maxIdleTime;
    }

    /**
     * Creates config object.
     *
     * @param ttl         - time to live for key\value entry in milliseconds. If
     *                    <code>0</code> then time to live doesn't affect entry
     *                    expiration.
     * @param maxIdleTime - max idle time for key\value entry in milliseconds.
     *                    <p>
     *                    if <code>maxIdleTime</code> and <code>ttl</code>
     *                    params are equal to <code>0</code> then entry stores
     *                    infinitely.
     * @param timeUnit    the time unit
     */
    public CacheConfig(long ttl, long maxIdleTime, TimeUnit timeUnit) {
        super();
        this.ttl = ttl;
        this.maxIdleTime = maxIdleTime;
        this.timeUnit = timeUnit;
    }

    /**
     * Creates config object.
     *
     * @param ttl         - time to live for key\value entry in milliseconds. If
     *                    <code>0</code> then time to live doesn't affect entry
     *                    expiration.
     * @param maxIdleTime - max idle time for key\value entry in milliseconds.
     *                    <p>
     *                    if <code>maxIdleTime</code> and <code>ttl</code>
     *                    params are equal to <code>0</code> then entry stores
     *                    infinitely.
     * @param maxSize     the max size
     */
    public CacheConfig(long ttl, long maxIdleTime, int maxSize) {
        super();
        this.ttl = ttl;
        this.maxIdleTime = maxIdleTime;
        this.maxSize = maxSize;
    }

    /**
     * Creates config object.
     *
     * @param ttl         - time to live for key\value entry in milliseconds. If
     *                    <code>0</code> then time to live doesn't affect entry
     *                    expiration.
     * @param maxIdleTime - max idle time for key\value entry in milliseconds.
     *                    <p>
     *                    if <code>maxIdleTime</code> and <code>ttl</code>
     *                    params are equal to <code>0</code> then entry stores
     *                    infinitely.
     * @param timeUnit    the time unit
     * @param maxSize     the max size
     */
    public CacheConfig(long ttl, long maxIdleTime, TimeUnit timeUnit, int maxSize) {
        super();
        this.ttl = ttl;
        this.maxIdleTime = maxIdleTime;
        this.timeUnit = timeUnit;
        this.maxSize = maxSize;
    }

    /**
     * Gets the ttl.
     *
     * @return the ttl
     */
    public long getTTL() {
        return ttl;
    }

    /**
     * Set time to live for key\value entry in milliseconds.
     *
     * @param ttl - time to live for key\value entry in milliseconds. If
     *            <code>0</code> then time to live doesn't affect entry
     *            expiration.
     */
    public void setTTL(long ttl) {
        this.ttl = ttl;
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
     * Gets the max idle time.
     *
     * @return the max idle time
     */
    public long getMaxIdleTime() {
        return maxIdleTime;
    }

    /**
     * Set max idle time for key\value entry in milliseconds.
     *
     * @param maxIdleTime - max idle time for key\value entry in milliseconds.
     *                    If <code>0</code> then max idle time doesn't affect
     *                    entry expiration.
     */
    public void setMaxIdleTime(long maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
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
}