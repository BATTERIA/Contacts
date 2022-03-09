package com.d.microsoft.contacts.model.decode

import com.d.microsoft.contacts.model.bean.Contact
import org.json.JSONArray
import org.json.JSONObject

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/8
 * @description:
 */
interface IContactDecoder {
    fun jsonToContacts(json: String): List<Contact>?

    fun jsonToContacts(jsonArray: JSONArray): List<Contact>?

    fun jsonToContact(json: String): Contact?

    fun jsonToContact(jsonObject: JSONObject): Contact?
}