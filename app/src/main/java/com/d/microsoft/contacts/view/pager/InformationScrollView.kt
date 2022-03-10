package com.d.microsoft.contacts.view.pager

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.widget.ScrollView

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: ScrollView for Information
 * In case there is too much information, we need to use ScrollView.
 * And I have to solve the sliding conflict between this and ViewPager2 outside.
 */
class InformationScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ScrollView(context, attrs) {
    // the start Y of each motion
    private var startY: Float = 0f

    // no need for listening click event
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return super.onTouchEvent(ev)

        when (ev.action) {
            ACTION_DOWN -> {
                startY = ev.y
            }
            ACTION_MOVE -> {
                // judge whether to move up and reach the top
                val isReachTop = startY < ev.y && scrollY == 0
                // judge whether to move down and reach the bottom
                val isReachBottom = if (0 == childCount) {
                    // edge case
                    false
                } else {
                    startY > ev.y && getChildAt(0).measuredHeight <= scrollY + height
                }
                // disallow parent ViewGroup to intercept TouchEvent when not at the top or bottom
                parent.requestDisallowInterceptTouchEvent(!isReachTop && !isReachBottom)
            }
            ACTION_UP -> {
                startY = 0f
                // when ACTION_UP, set this flag to false
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }

        return super.onTouchEvent(ev)
    }
}