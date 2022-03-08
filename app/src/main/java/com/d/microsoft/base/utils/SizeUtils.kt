package com.d.microsoft.base.utils

import android.content.res.Resources

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: Utils for something like dp2px
 */
object SizeUtils: Logger {
    private const val TAG = "SizeUtils"

    override val logTag: String
        get() = TAG

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    fun dp2px(dpValue: Float): Int {
        return try {
            val scale = Resources.getSystem().displayMetrics.density
            (dpValue * scale + 0.5f).toInt()
        } catch (e: Exception) {
            logError { "dp2px error msg: ${e.message}" }
            0
        }
    }
}