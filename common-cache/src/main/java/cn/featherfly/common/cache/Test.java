
package cn.featherfly.common.cache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import cn.featherfly.common.cache.caffeine.CaffeineCachingProvider;

/**
 * <p>
 * Test
 * </p>
 *
 * @author zhongj
 */
public class Test {

    public static void jsr107() throws InterruptedException {
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

    static void testExpire(javax.cache.Cache<Object, Object> cache1, javax.cache.Cache<Object, Object> cache2,
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

    static void testExpire() throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder().recordStats().expireAfterWrite(2, TimeUnit.SECONDS).build();
        Cache<Object, Object> cache2 = Caffeine.newBuilder().recordStats().expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
        Cache<Object, Object> cache3 = Caffeine.newBuilder().recordStats().expireAfterAccess(1, TimeUnit.SECONDS)
                .expireAfterWrite(2, TimeUnit.SECONDS).build();

        String key = "key";
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";
        String s4 = "s4";

        cache.put(key, s1);
        cache2.put(key, s1);
        cache3.put(key, s1);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
        Thread.sleep(800);
        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
        System.out.println("cache2.getIfPresent(key) " + cache2.getIfPresent(key));
        System.out.println("cache3.getIfPresent(key) " + cache3.getIfPresent(key));
    }

    //    static void testNoExpire() throws InterruptedException {
    //        Cache<Object, Object> cache = Caffeine.newBuilder().
    //        String key = "key";
    //        String s1 = "s1";
    //
    //        cache.put(key, s1);
    //        System.out.println("cache.getIfPresent(key) " + cache.getIfPresent(key));
    //    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(CaffeineCachingProvider.DEFAULT_URI);
        jsr107();
    }
}
