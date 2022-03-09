package com.d.microsoft.contacts.view.tab

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.d.microsoft.base.utils.SizeUtils
import com.google.android.material.imageview.ShapeableImageView

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: AvatarView based on ShapebleImageView
 * A thick stroke as an indicator will show when avatar is selected.
 */
class AvatarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ShapeableImageView(context, attrs) {
    override fun onDraw(canvas: Canvas?) {
        strokeWidth = if (isSelected) SizeUtils.dp2px(INDICATOR_WIDTH).toFloat() else 0f
        super.onDraw(canvas)
    }

    companion object {
        private const val INDICATOR_WIDTH = 5f
    }
}