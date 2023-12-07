package com.example.demo.infrastructure.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheConfiguration {

    @Autowired
    private CacheManager cacheManager;

    /**
     * Automatically clear the cache for each 120(sec).
     */
    @Scheduled(fixedRate = 120_000)
    public void evictAllCacheValues() {
        System.out.println("Preparing to clear caches!!!");
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        System.out.println("Caches are now empty.");
    }

}
