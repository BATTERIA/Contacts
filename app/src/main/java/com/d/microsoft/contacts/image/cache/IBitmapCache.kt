package com.d.microsoft.contacts.image.cache

import android.graphics.Bitmap

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description:
 */
interface IBitmapCache {
    fun get(key: String): Bitmap?
    fun put(key: String, bitmap: Bitmap)
}