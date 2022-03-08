package com.d.microsoft.contacts.cache

import com.d.microsoft.base.utils.ContextUtils
import com.d.microsoft.contacts.bean.Contact
import com.d.microsoft.contacts.decode.ContactDecoder
import com.d.microsoft.contacts.decode.IContactDecoder

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description: DiskCache
 */
class ContactDiskCache : IContactCache {
    private val contactDecoder: IContactDecoder = ContactDecoder()

    override fun get(): List<Contact> {
        try {
            val inputStream = ContextUtils.application?.assets?.open(RES_PATH)
            inputStream?.let {
                // UTF-8 is default choice
                val data = String(it.readBytes())
                it.close()
                // json string convert to entity class Contact
                return contactDecoder.jsonToContacts(data) ?: emptyList()
            }
        } catch (e: Exception) {
            return emptyList()
        }
        return emptyList()
    }

    // not required currently
    override fun put(list: List<Contact>) = Unit

    companion object {
        private const val RES_PATH = "data/contacts.json"
    }
}