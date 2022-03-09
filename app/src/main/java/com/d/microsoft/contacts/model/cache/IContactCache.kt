package com.d.microsoft.contacts.model.cache

import com.d.microsoft.contacts.model.bean.Contact

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: Cache Interface
 * It's enough for now, but the access interface may need to be adjusted if necessary
 */
interface IContactCache {
    fun get(): List<Contact>
    fun put(list: List<Contact>)
}