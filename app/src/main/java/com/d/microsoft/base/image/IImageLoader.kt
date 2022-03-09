package com.d.microsoft.base.image

import android.graphics.Bitmap

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: ImageLoader Interface
 */
interface IImageLoader {
    fun execute(request: ImageRequest): Bitmap?
    fun enqueue(request: ImageRequest, callback: BitmapCallback)
    fun release()
}

/**
 * callback when bitmap is got
 */
typealias BitmapCallback = (bitmap: Bitmap?) -> Unit