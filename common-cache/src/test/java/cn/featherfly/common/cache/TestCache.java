
package cn.featherfly.common.cache;

import static org.testng.AssertJUnit.assertNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheException;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;

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

    //    @Test
    //    public void jsr107() throws InterruptedException {
    //        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<>();
    //
    //        mutableConfiguration.setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(Duration.ofSeconds(2)));
    //        //        ExpiryPolicy expiryPolicy = mutableConfiguration.getExpiryPolicyFactory().create();
    //        //        System.out.println(expiryPolicy.getClass());
    //        //        System.out.println(expiryPolicy.getExpiryForAccess());
    //        //        System.out.println(expiryPolicy.getExpiryForCreation());
    //        //        System.out.println(expiryPolicy.getExpiryForUpdate());
    //        CachingProvider cachingProvider = Caching.getCachingProvider();
    //
    //        System.out.println(cachingProvider);
    //
    //        CacheManager cacheManager = cachingProvider.getCacheManager();
    //
    //        javax.cache.Cache<Object, Object> cache1 = cacheManager.createCache("cache1", mutableConfiguration);
    //        mutableConfiguration
    //                .setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(Duration.ofSeconds(2), Duration.ofSeconds(1)));
    //        javax.cache.Cache<Object, Object> cache2 = cacheManager.createCache("cache2", mutableConfiguration);
    //        mutableConfiguration.setExpiryPolicyFactory(ExpiryPolicyImpl.factoryOf(null, Duration.ofSeconds(1)));
    //        javax.cache.Cache<Object, Object> cache3 = cacheManager.createCache("cache3", mutableConfiguration);
    //
    //        testExpire(cache1, cache2, cache3);
    //
    //        //
    //        //        cacheManager.createCache("a", mutableConfiguration);
    //        //
    //        System.out.println(cacheManager);
    //
    //        //        MutableConfiguration<String, String> config = new MutableConfiguration();
    //        //        Cache<String, String> cache = cacheManager.createCache("JDKCodeNames", config);
    //        //
    //        //        cache.put("JDK1.5", "Tiger");
    //        //
    //        //        cache.put("JDK1.6", "Mustang");
    //        //
    //        //        cache.put("JDK1.7", "Dolphin");
    //        //
    //        //        String jdk7CodeName = cache.get("JDK1.7");
    //    }

    @Test
    public void testCacheManager() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig();
        c.setMaxSize(10);
        cacheConfigs.put("cache1", c);

        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            testExpire(cm.getCache("cache1"), 5, 1000);
        }
    }

    @Test
    public void testCacheManager1() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(ExpiryPolicys.ACCESSED, 800, TimeUnit.MILLISECONDS);
        //        c.setMaxSize(2);
        cacheConfigs.put("cache1", c);
        // 测试以缓存的最后读取时间为缓存计时时间
        Object value;
        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            javax.cache.Cache<Object, Object> cache1 = cm.getCache("cache1");
            testExpire(cache1, 5, 500);
            Thread.sleep(800);
            value = cache1.get("key");
            System.out.println("cache1.getIfPresent(key) " + value);
            assertNull(value);

            System.out.println();
            System.out.println("cache1.put(\"key\", \"s2\"); ");
            cache1.put("key", "s2");
            Thread.sleep(800);
            value = cache1.get("key");
            System.out.println("cache1.getIfPresent(key) " + value);
            assertNull(value);
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
        CacheConfig c = new CacheConfig(ExpiryPolicys.CREATED, 2);
        c.setMaxSize(10);
        cacheConfigs.put("cache1", c);
        cacheConfigs.put("cache2", new CacheConfig(ExpiryPolicys.CREATED, 1));
        cacheConfigs.put("cache3", new CacheConfig(ExpiryPolicys.CREATED, 1));

        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            testExpire(cm.getCache("cache1"), cm.getCache("cache2"), cm.getCache("cache3"));
        }
    }

    @Test
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

    @Test
    void testExpire(javax.cache.Cache<Object, Object> cache1) throws InterruptedException {
        testExpire(cache1, 4, 800);
    }

    @Test
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

    @Test
    void testListener() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(ExpiryPolicys.ACCESSED, 800, TimeUnit.MILLISECONDS);
        //        c.setMaxSize(2);
        String cacheName = "cache1";
        cacheConfigs.put(cacheName, c);
        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            javax.cache.Cache<String, String> cache = cm.getCache(cacheName);
            // YUFEI_TODO 优化cache entry event type
            // 加入一个Configuration，默认加入一个CacheEntryListener实现, 可以动态添加各种事件监听add(EventType, Listener)
            MutableCacheEntryListenerConfiguration<String, String> listener = new MutableCacheEntryListenerConfiguration<>(
                    () -> {
                        return new CacheEntryListener<>();
                    }, () -> event -> {
                        System.out.println("event = " + event.getEventType());
                        System.out.println("  key = " + event.getKey());
                        System.out.println("  value = " + event.getValue());
                        System.out.println("  old value = " + event.getOldValue());
                        return true;
                    }, false, false);
            cache.registerCacheEntryListener(listener);

            cache.put("key1", "value1");
            cache.put("key2", "value2");
            cache.put("key3", "value3");
            cache.put("key1", "value11");

            cache.remove("key2");

            Thread.sleep(800);

            cache.put("key1", "value1");
            cache.put("key2", "value2");
            cache.put("key3", "value3");

            Thread.sleep(1000);

        }
    }

    @Test
    void testListener2() throws InterruptedException {
        Map<String, CacheConfig> cacheConfigs = new HashMap<>();
        CacheConfig c = new CacheConfig(ExpiryPolicys.ACCESSED, 800, TimeUnit.MILLISECONDS);
        //        c.setMaxSize(2);
        String cacheName = "cache1";
        cacheConfigs.put(cacheName, c);
        try (cn.featherfly.common.cache.CacheManager cm = new cn.featherfly.common.cache.CacheManager(cacheConfigs)) {
            javax.cache.Cache<String, String> cache = cm.getCache(cacheName);
            MulitiCacheEntryListenerConfiguration<String, String> listener = new MulitiCacheEntryListenerConfiguration<>(
                    true);
            listener.addListener((CacheEntryCreatedListener<String, String>) events -> {
                //                try {
                //                    Thread.sleep(10);
                //                } catch (InterruptedException e) {
                //                }
                System.out.println("CacheEntryCreatedListener");
                Iterator<CacheEntryEvent<? extends String, ? extends String>> it = events.iterator();
                while (it.hasNext()) {
                    CacheEntryEvent<? extends String, ? extends String> event = it.next();
                    System.out.println("  event = " + event.getEventType());
                    System.out.println("    key = " + event.getKey());
                    System.out.println("    value = " + event.getValue());
                    System.out.println("    old value = " + event.getOldValue());
                }
                System.out.println();
                //                System.out.println(.getEventType());
            }).addFilter((CacheEntryEventFilter<String, String>) event -> {
                System.out.println("CacheEntryEventFilter");
                System.out.println("  event = " + event.getEventType());
                System.out.println("    key = " + event.getKey());
                System.out.println("    value = " + event.getValue());
                System.out.println("    old value = " + event.getOldValue());
                System.out.println();
                return true;
            });
            cache.registerCacheEntryListener(listener);

            cache.put("key1", "value1");
            cache.put("key2", "value2");
            cache.put("key3", "value3");
            cache.put("key1", "value11");

            cache.remove("key2");

            Thread.sleep(800);

            cache.put("key1", "value1");
            cache.put("key2", "value2");
            cache.put("key3", "value3");

            Thread.sleep(1000);

        }
    }

    @Test(expectedExceptions = CacheException.class)
    void testListenerException() {
        MulitiCacheEntryListenerConfiguration<String, String> listener = new MulitiCacheEntryListenerConfiguration<>();
        listener.setCacheEntryEventFilterFactory(() -> event -> false);
    }

    @Test(expectedExceptions = CacheException.class)
    void testListenerException2() {
        MulitiCacheEntryListenerConfiguration<String, String> listener = new MulitiCacheEntryListenerConfiguration<>();
        listener.setCacheEntryListenerFactory(() -> null);
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
