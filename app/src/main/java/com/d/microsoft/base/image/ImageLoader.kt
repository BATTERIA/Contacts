package com.d.microsoft.base.image

import android.graphics.Bitmap
import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logError
import com.d.microsoft.base.image.cache.BitmapCacheManager
import kotlinx.coroutines.*

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: ImageLoader
 * use coroutine to async load
 * use three-level cache
 * support get image from asset
 */
class ImageLoader : IImageLoader, Logger {
    private val cacheManager: BitmapCacheManager = BitmapCacheManager()
    private val coroutineScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main + CoroutineExceptionHandler { _, t ->
            logError { "Coroutine error msg: ${t.message}" }
        })

    override fun execute(request: ImageRequest): Bitmap? = cacheManager.get(request)

    override fun enqueue(request: ImageRequest, callback: BitmapCallback) {
        // start a coroutine to finish the ImageRequest
        coroutineScope.launch(Dispatchers.Default) {
            val bitmap = cacheManager.get(request)
            withContext(Dispatchers.Main) {
                callback(bitmap)
            }
        }
    }

    /**
     * release CoroutineScope
     */
    override fun release() {
        coroutineScope.cancel()
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "ImageLoader"
    }
}