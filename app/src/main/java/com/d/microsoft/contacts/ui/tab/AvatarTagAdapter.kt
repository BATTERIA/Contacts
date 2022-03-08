package com.d.microsoft.contacts.ui.tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d.microsoft.R
import com.d.microsoft.contacts.bean.Contact
import com.d.microsoft.contacts.image.AssetImageRequest
import com.d.microsoft.contacts.image.IImageLoader
import com.d.microsoft.contacts.image.ImageLoader
import com.google.android.material.imageview.ShapeableImageView

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description: adapter for AvatarTagLayout
 * For achieving the goal effect, I used LinearSnapHelper to let avatar keep center.
 * And in order to let the first and last one to keep center,
 * I used two placeholder to achieve the effect.
 */
class AvatarTagAdapter(private val onTagClick: OnTagClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val imageLoader: IImageLoader = ImageLoader()
    private val contacts: ArrayList<Contact> = ArrayList()

    /**
     * add contacts
     */
    fun addContacts(list: List<Contact>) {
        contacts.addAll(list)
    }

    /**
     * to do some release work
     */
    fun release() {
        imageLoader.release()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (ViewType.PLACEHOLDER.ordinal == viewType) {
            // build placeholder
            val view = View(parent.context)
            val viewWidth = (parent.measuredWidth - parent.measuredHeight) / 2
            val params =
                RecyclerView.LayoutParams(viewWidth, RecyclerView.LayoutParams.MATCH_PARENT)
            view.layoutParams = params
            return PlaceHolderViewHolder(view)
        }

        // build AvatarViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        val params = RecyclerView.LayoutParams(parent.measuredHeight, parent.measuredHeight)
        view.layoutParams = params
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isPlaceHolder(position)) return

        (holder as? AvatarViewHolder)?.ivAvatar?.let { avatar ->
            // callback when avatar tag is clicked
            avatar.setOnClickListener {
                onTagClick(position - 1)
            }

            // get bitmap from asset asynchronously
            imageLoader.enqueue(AssetImageRequest("avatars/${contacts[position - 1].avatarFileName}")) {
                it?.let { avatar.setImageBitmap(it) }
            }
        }
    }

    override fun getItemCount(): Int = contacts.size + 2

    override fun getItemViewType(position: Int): Int =
        if (isPlaceHolder(position)) {
            ViewType.PLACEHOLDER.ordinal
        } else {
            ViewType.AVATAR.ordinal
        }

    /**
     * the first and last items are placeholder
     */
    private fun isPlaceHolder(position: Int): Boolean = 0 == position || itemCount - 1 == position

    /**
     * ViewHolder for avatar
     */
    class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_avatar)
    }

    /**
     * ViewHolder for placeholder
     */
    class PlaceHolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /**
     * ViewType
     * to distinguish between avatar tag and placeholder
     */
    enum class ViewType {
        AVATAR, PLACEHOLDER
    }
}

/**
 * Callback when avatar tag is clicked
 */
typealias OnTagClick = (Int) -> Unit
