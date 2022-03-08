package com.d.microsoft.base.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: Utils about resources
 */
object ResourceUtils : Logger {
    private const val TAG = "ResourceUtils"

    override val logTag: String
        get() = TAG

    /**
     * get bitmap from asset
     * [path] the file path
     * [Bitmap] it can be null if occur error
     */
    fun getBitmapFromAsset(path: String): Bitmap? {
        try {
            val inputStream = ContextUtils.application?.assets?.open(path)
            inputStream?.let {
                val bitmap = BitmapFactory.decodeStream(it)
                it.close()
                return bitmap
            }
        } catch (e: Exception) {
            logError { "getBitmapFromAsset error msg: ${e.message}" }
        }
        return null
    }
}