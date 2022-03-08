package com.d.microsoft.contacts.ui.tab

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logDebug

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description: AvatarTagLayoutMediator
 * like TabLayoutMediator to combine with ViewPager2
 */
class AvatarTagLayoutMediator(
    private val tagLayout: AvatarTagLayout,
    private val viewPager: ViewPager2
) : Logger {
    /**
     * combine between ViewPager2 and AvatarTagLayout
     */
    fun attach() {
        tagLayout.addOnScrollListener(onScrollListener)
        viewPager.registerOnPageChangeCallback(onPageChangeCallback)

        // match together
        tagLayout.scrollToTab(viewPager.currentItem)
    }

    /**
     * update ViewPager2 when AvatarTagLayout scrolling
     */
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        // previous scroll state
        private var previousState = ViewPager2.SCROLL_STATE_IDLE
        // current scroll state
        private var currentState = ViewPager2.SCROLL_STATE_IDLE

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            logDebug { "AvatarTagLayout onScrollStateChanged newState: $newState" }

            previousState = currentState
            currentState = newState

            when (newState) {
                ViewPager2.SCROLL_STATE_DRAGGING -> {
                    viewPager.beginFakeDrag()
                }
                ViewPager2.SCROLL_STATE_IDLE -> {
                    viewPager.endFakeDrag()
                    if (previousState == ViewPager2.SCROLL_STATE_SETTLING) {
                        // when scrolling end, correct the ViewPager2 and refresh the indicator
                        viewPager.setCurrentItem(tagLayout.currentPage, true)
                        tagLayout.refreshIndicator()
                    }
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val viewPagerStep = -(dx / tagLayout.measuredHeight.toFloat() * viewPager.measuredHeight)
            logDebug { "AvatarTagLayout onScrolled viewPagerStep: $viewPagerStep" }
            viewPager.fakeDragBy(viewPagerStep)
        }
    }

    /**
     * update AvatarTagLayout when ViewPager2 scrolling
     */
    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (viewPager.isFakeDragging) return
            logDebug { "ViewPager2 onScrolled position: $position, positionOffset: $positionOffset" }
            tagLayout.scrollToTab(position, positionOffset)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (viewPager.isFakeDragging) return
            logDebug { "ViewPager2 onScrolled position: $position" }
            tagLayout.scrollToTab(position)
        }
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "AvatarTagLayoutMediator"
    }
}