package com.d.microsoft.contacts.view.tab

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logDebug
import com.d.microsoft.base.utils.logError

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/5
 * @description: custom RecyclerView for avatar view
 *
 * For achieving the goal effect, TabLayout is not enough,
 * so I used LinearSnapHelper to let avatar keep center.
 */
class AvatarTagLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), Logger {
    // get current page
    val currentPage: Int
        get() {
            val snapView = linearSnapHelper.findSnapView(linearLayoutManager)
            return if (null == snapView) {
                logError { "getCurrentPage findSnapView error" }
                0
            } else {
                linearLayoutManager.getPosition(snapView) - 1
            }
        }

    // horizontal LinearLayoutManager
    private val linearLayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    // use LinearSnapHelper to let avatar keep center
    private val linearSnapHelper = LinearSnapHelper()

    init {
        layoutManager = linearLayoutManager
        linearSnapHelper.attachToRecyclerView(this)
    }

    /**
     * refresh current indicator
     */
    fun refreshIndicator() {
        setIndicator(currentPage)
    }

    /**
     * scroll to the target position
     */
    fun scrollToTab(position: Int) {
        scrollToTab(position, 0f)
        setIndicator(position)
    }

    /**
     * scroll to the target position with offset
     */
    fun scrollToTab(position: Int, positionOffset: Float) {
        val offset =
            (measuredWidth - measuredHeight) / 2 - (measuredHeight * positionOffset).toInt()
        logDebug { "scrollToTab $position $offset" }
        linearLayoutManager.scrollToPositionWithOffset(position + 1, offset)
    }

    /**
     * set indicator actively
     */
    private fun setIndicator(position: Int) {
        for (i in 1 until linearLayoutManager.itemCount) {
            val isSelected = i == position + 1
            linearLayoutManager.findViewByPosition(i)?.isSelected = isSelected
        }
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "AvatarTagLayout"
    }
}