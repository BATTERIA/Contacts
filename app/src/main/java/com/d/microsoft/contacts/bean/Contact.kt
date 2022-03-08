package com.d.microsoft.contacts.bean

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description: Contact bean
 */
data class Contact(
    val firstName: String,
    val lastName: String,
    val avatarFileName: String,
    val title: String,
    val introduction: String
) {
    /**
     * combine whole name
     */
    fun getWholeName(): SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        val first = SpannableString(firstName)
        // first name should be bold
        first.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            first.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        builder.append(first)
        builder.append(" ")
        builder.append(lastName)
        return builder
    }
}
