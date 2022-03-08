package com.d.microsoft.contacts.cache

import com.d.microsoft.contacts.bean.Contact

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: MemoryCache
 */
class ContactMemoryCache : IContactCache {
    private val contacts: ArrayList<Contact> = ArrayList()

    override fun get(): List<Contact> = contacts

    override fun put(list: List<Contact>) {
        contacts.addAll(list)
    }
}