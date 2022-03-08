package com.d.microsoft.contacts.image.cache

import android.graphics.Bitmap
import com.d.microsoft.base.utils.ResourceUtils
import com.d.microsoft.contacts.image.AssetImageRequest
import com.d.microsoft.contacts.image.ImageRequest

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: Bitmap three-level cache
 * support get image from asset
 */
class BitmapCacheManager {
    private val memoryCache: IBitmapCache = BitmapMemoryCache()
    private val diskContact: IBitmapCache = BitmapDiskCache()

    fun get(request: ImageRequest): Bitmap? {
        val key = request.generateKey()
        // first find from memory
        val bitmapFromMemory = memoryCache.get(key)
        if (null != bitmapFromMemory) {
            return bitmapFromMemory
        }

        if (request is AssetImageRequest) {
            // if request is AssetImageRequest, get bitmap from asset
            val bitmapFromAsset = ResourceUtils.getBitmapFromAsset(request.path)
            bitmapFromAsset?.let { memoryCache.put(key, it) }
            return bitmapFromAsset
        }

        // todo: get from disk if necessary

        // todo: get from network if necessary

        return null
    }
}