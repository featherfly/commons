
package cn.featherfly.common.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.testng.annotations.Test;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * <p>
 * Test
 * </p>
 *
 * @author zhongj
 */
public class TestCache {

    @Test
    public void jsr107() throws InterruptedException {
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<>();

        mutableConfiguration.setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(Duration.ofSeconds(2)));
        //        ExpiryPolicy expiryPolicy = mutableConfiguration.getExpiryPolicyFactory().create();
        //        System.out.println(expiryPolicy.getClass());
        //        System.out.println(expiryPolicy.getExpiryForAccess());
        //        System.out.println(expiryPolicy.getExpiryForCreation());
        //        System.out.println(expiryPolicy.getExpiryForUpdate());
        CachingProvider cachingProvider = Caching.getCachingProvider();

        System.out.println(cachingProvider);

        CacheManager cacheManager = cachingProvider.getCacheManager();

        javax.cache.Cache<Object, Object> cache1 = cacheManager.createCache("cache1", mutableConfiguration);
        mutableConfiguration
                .setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(Duration.ofSeconds(2), Duration.ofSeconds(1)));
        javax.cache.Cache<Object, Object> cache2 = cacheManager.createCache("cache2", mutableConfiguration);
        mutableConfiguration.setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(null, Duration.ofSeconds(1)));
        javax.cache.Cache<Object, Object> cache3 = cacheManager.createCache("cache3", mutableConfiguration);

        testExpire(cache1, cache2, cache3);

        //
        //        cacheManager.createCache("a", mutableConfiguration);
        //
        System.out.println(cacheManager);

        //        MutableConfiguration<String, String> config = new MutableConfiguration();
        //        Cache<String, String> cache = cacheManager.createCache("JDKCodeNames", config);
        //
        //        cache.put("JDK1.5", "Tiger");
        //
        //        cache.put("JDK1.6", "Mustang");
        //
        //        cache.put("JDK1.7", "Dolphin");
        //
        //        String jdk7CodeName = cache.get("JDK1.7");
    }

    @Test
    public void testCacheManager() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(0);
        c.setMaxSize(10);
        cacheConfigs.put("cache1", c);

        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            testExpire(cm.getCache("cache1"), 5, 1000);
        }
    }

    @Test
    public void testCacheManager1() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(800, TimeUnit.MILLISECONDS);
        //        c.setMaxSize(2);
        cacheConfigs.put("cache1", c);
        // 测试以缓存的最后读取时间为缓存计时时间
        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            javax.cache.Cache<Object, Object> cache1 = cm.getCache("cache1");
            testExpire(cache1, 5, 500);
            Thread.sleep(1000);
            System.out.println("cache1.getIfPresent(key) " + cache1.get("key"));

            cache1.put("key", "s2");
            Thread.sleep(1000);
            System.out.println("cache1.getIfPresent(key) " + cache1.get("key"));
        }
    }

    @Test
    public void testCacheManager2() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig();
        cacheConfigs.put("cache1", c);

        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            testExpire(cm.getCache("cache1"), 1000, 1000);
        }
    }

    @Test
    public void testCacheManager3() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(2);
        c.setMaxSize(10);
        cacheConfigs.put("cache1", c);
        cacheConfigs.put("cache2", new CacheConfig(1));
        cacheConfigs.put("cache3", new CacheConfig(1));

        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            testExpire(cm.getCache("cache1"), cm.getCache("cache2"), cm.getCache("cache3"));
        }
    }

    void testExpire(javax.cache.Cache<Object, Object> cache1, int times, int delay) throws InterruptedException {
        String key = "key";
        String s1 = "s1";
        cache1.put(key, s1);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        for (int i = 0; i < times; i++) {
            Thread.sleep(delay);
            System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        }
    }

    void testExpire(javax.cache.Cache<Object, Object> cache1) throws InterruptedException {
        testExpire(cache1, 4, 800);
    }

    void testExpire(javax.cache.Cache<Object, Object> cache1, javax.cache.Cache<Object, Object> cache2,
            javax.cache.Cache<Object, Object> cache3) throws InterruptedException {

        String key = "key";
        String s1 = "s1";

        cache1.put(key, s1);
        cache2.put(key, s1);
        cache3.put(key, s1);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.get(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.get(key));
        Thread.sleep(800);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.get(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.get(key));
        Thread.sleep(800);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.get(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.get(key));
        Thread.sleep(800);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.get(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.get(key));
        Thread.sleep(800);
        System.out.println("cache1.getIfPresent(key) " + cache1.get(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.get(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.get(key));
    }

    @Test
    void testExpire() throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder().recordStats().expireAfterWrite(2, TimeUnit.SECONDS).build();
        Cache<Object, Object> cache2 = Caffeine.newBuilder().recordStats().expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        Cache<Object, Object> cache3 = Caffeine.newBuilder().recordStats().expireAfterAccess(1, TimeUnit.SECONDS)
                .expireAfterWrite(2, TimeUnit.SECONDS).build();
        Cache<Object, Object> cache4 = Caffeine.newBuilder().recordStats().build();

        String key = "key";
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";
        String s4 = "s4";

        cache.put(key, s1);
        cache2.put(key, s1);
        cache3.put(key, s1);
        cache4.put(key, s1);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));

        while (cache4.getIfPresent(key) != null) {
            Thread.sleep(1000);
            System.out.println("cache4.getIfPresent(key) " + cache4.getIfPresent(key));
        }
    }

    //    static void testNoExpire() throws InterruptedException {
    //        Cache<Object, Object> cache = Caffeine.newBuilder().
    //        String key = "key";
    //        String s1 = "s1";
    //
    //        cache.put(key, s1);
    //        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
    //    }
}
