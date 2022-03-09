package com.d.microsoft.base.image

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: base ImageRequest
 */
abstract class ImageRequest(val config: ImageConfig?) {
    /**
     * generate key for cache
     */
    abstract fun generateKey(): String
}