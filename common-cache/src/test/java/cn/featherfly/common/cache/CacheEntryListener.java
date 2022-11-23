
/*
 * All rights Reserved, Designed By zhongj
 * @Title: CacheEntryListener.java
 * @Package cn.featherfly.common.cache
 * @Description: CacheEntryListener
 * @author: zhongj
 * @date: 2022-11-22 17:39:22
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.cache;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;
import javax.cache.event.EventType;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * CacheEntryListener.
 *
 * @author zhongj
 */
public class CacheEntryListener<K, V> implements CacheEntryCreatedListener<K, V>, CacheEntryUpdatedListener<K, V>,
        CacheEntryRemovedListener<K, V>, CacheEntryExpiredListener<K, V> {

    public CacheEntryListener() {
    }

    public boolean isCompatible(@NonNull EventType eventType) {
        System.out.println("isCompatible");
        return true;
    }

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        System.out.println("onCreated");
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        System.out.println("onUpdated");
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        System.out.println("onRemoved");
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) {
        System.out.println("onExpired");
    }
}
