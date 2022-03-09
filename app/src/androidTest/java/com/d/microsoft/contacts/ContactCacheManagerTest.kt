package com.d.microsoft.contacts

import com.d.microsoft.contacts.model.cache.ContactCacheManager
import com.d.microsoft.contacts.model.cache.IContactCache
import junit.framework.TestCase

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/9
 * @description: UT for ContactCacheManager
 */
class ContactCacheManagerTest : TestCase() {
    private var contactCacheManager: IContactCache? = null

    public override fun setUp() {
        super.setUp()
        contactCacheManager = ContactCacheManager()
    }

    /**
     * Presently the fixed number of contacts is 28.
     */
    fun testGet() {
        val list = contactCacheManager?.get()
        assertEquals(list?.size, 28)

        list?.get(0)?.apply {
            assertEquals(firstName, "Allan")
            assertEquals(lastName, "Munger")
            assertEquals(avatarFileName, "Allan Munger.png")
            assertEquals(title, "Writer")
        }
    }
}