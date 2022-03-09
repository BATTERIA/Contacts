package com.d.microsoft.contacts.view.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d.microsoft.R
import com.d.microsoft.contacts.model.bean.Contact

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description: adapter for detail ViewPager2
 */
class DetailPageAdapter : RecyclerView.Adapter<DetailPageAdapter.DetailViewHolder>() {
    private val contacts: ArrayList<Contact> = ArrayList()

    /**
     * add contacts
     */
    fun addContacts(list: List<Contact>) {
        contacts.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_page, parent, false))

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.tvName.text = contacts[position].getWholeName()
        holder.tvTitle.text = contacts[position].title
        holder.tvIntroduction.text = contacts[position].introduction
    }

    override fun getItemCount(): Int = contacts.size

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvIntroduction: TextView = itemView.findViewById(R.id.tv_introduction)
    }
}