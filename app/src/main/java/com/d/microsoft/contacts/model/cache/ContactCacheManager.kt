package com.d.microsoft.contacts.model.cache

import com.d.microsoft.contacts.model.bean.Contact

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: three-level cache
 */
class ContactCacheManager: IContactCache {
    private val memoryCache: IContactCache = ContactMemoryCache()
    private val diskCache: IContactCache = ContactDiskCache()

    override fun get(): List<Contact> {
        // first find from memory
        val listFromMemory = memoryCache.get()
        if (listFromMemory.isNotEmpty()) {
            return listFromMemory
        }
        // second find from disk
        val listFromDisk = diskCache.get()
        if (listFromDisk.isNotEmpty()) {
            memoryCache.put(listFromDisk)
            return listFromDisk
        }
        // todo: get from network if necessary
        return emptyList()
    }

    // not required currently
    override fun put(list: List<Contact>) = Unit
}