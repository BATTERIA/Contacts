package com.d.microsoft.contacts

import com.d.microsoft.base.image.AssetImageRequest
import com.d.microsoft.base.image.IImageLoader
import com.d.microsoft.base.image.ImageLoader
import junit.framework.TestCase

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/9
 * @description: UT for ImageLoader
 */
class ImageLoaderTest : TestCase() {
    private var imageLoader: IImageLoader? = null

    public override fun setUp() {
        super.setUp()
        imageLoader = ImageLoader()
    }

    /**
     * test synchronous execution
     */
    fun testExecute() {
        val bitmap = imageLoader?.execute(AssetImageRequest(ASSET_PATH))
        assertEquals(null == bitmap, false)
    }

    /**
     * test asynchronous execution
     */
    fun testEnqueue() {
        imageLoader?.enqueue(AssetImageRequest(ASSET_PATH)) {
            assertEquals(null == it, false)
        }
    }

    /**
     * test synchronous execution with error path
     */
    fun testExecuteWithError() {
        val bitmap = imageLoader?.execute(AssetImageRequest(ERROR_ASSET_PATH))
        assertEquals(null == bitmap, true)
    }

    /**
     * test asynchronous execution with error path
     */
    fun testEnqueueWithError() {
        imageLoader?.enqueue(AssetImageRequest(ERROR_ASSET_PATH)) {
            assertEquals(null == it, true)
        }
    }

    /**
     * test release
     */
    fun testRelease() {
        imageLoader?.release()
        val bitmap = imageLoader?.execute(AssetImageRequest(ASSET_PATH))
        assertEquals(null == bitmap, false)

        imageLoader?.enqueue(AssetImageRequest(ASSET_PATH)) {
            assertEquals(null == it, true)
        }
    }

    companion object {
        private const val ASSET_PATH = "avatars/Allan Munger.png"
        private const val ERROR_ASSET_PATH = "xxx.png"
    }
}