package com.d.microsoft.contacts.image.cache

import android.graphics.Bitmap

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: DiskCache
 * not required currently
 */
class BitmapDiskCache : IBitmapCache {
    // todo: implementation if necessary
    override fun get(key: String): Bitmap? = null

    // todo: implementation if necessary
    override fun put(key: String, bitmap: Bitmap) = Unit
}