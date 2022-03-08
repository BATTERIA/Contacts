/*
 * Copyright (c) 2015-2019 BiliBili Inc.
 */

package com.d.microsoft.base.utils

import android.util.Log
import com.d.microsoft.base.utils.Logger.Companion.DEFAULT_TAG

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: Logger with default logTag
 */
interface Logger {
    val logTag: String
    companion object {
        const val DEFAULT_TAG = "Microsoft"
    }
}

inline fun Logger.logInfo(throwable: Throwable? = null, block: () -> String?) {
    Log.i("$DEFAULT_TAG-$logTag", block.invoke() ?: "")
}

inline fun Logger.logDebug(throwable: Throwable? = null, block: () -> String?) {
    Log.d("$DEFAULT_TAG-$logTag", block.invoke() ?: "")
}

inline fun Logger.logWarn(throwable: Throwable? = null, block: () -> String?) {
    Log.w("$DEFAULT_TAG-$logTag", block.invoke() ?: "")
}

inline fun Logger.logError(throwable: Throwable? = null, block: () -> String?) {
    Log.e("$DEFAULT_TAG-$logTag", block.invoke() ?: "")
}
