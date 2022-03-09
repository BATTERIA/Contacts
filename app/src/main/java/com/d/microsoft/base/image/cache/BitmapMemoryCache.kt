package com.d.microsoft.base.image.cache

import android.graphics.Bitmap
import android.util.LruCache

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: MemoryCache
 * implementation using LRU Cache
 */
class BitmapMemoryCache(maxCacheSize: Int = DEFAULT_MAX_CACHE) : IBitmapCache {
    private val cache: LruCache<String, Bitmap> = LruCache(maxCacheSize)

    override fun get(key: String): Bitmap? = cache[key]

    override fun put(key: String, bitmap: Bitmap) {
        cache.put(key, bitmap)
    }

    companion object {
        private const val DEFAULT_MAX_CACHE = 30
    }
}