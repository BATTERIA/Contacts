package com.d.microsoft

import android.app.Application
import com.d.microsoft.base.utils.ContextUtils

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description:
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextUtils.application = this
    }
}