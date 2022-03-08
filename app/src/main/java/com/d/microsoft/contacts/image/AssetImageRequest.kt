package com.d.microsoft.contacts.image

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: ImageRequest to get image from asset
 */
class AssetImageRequest(val path: String, config: ImageConfig? = null) : ImageRequest(config) {
    override fun generateKey(): String = path
}