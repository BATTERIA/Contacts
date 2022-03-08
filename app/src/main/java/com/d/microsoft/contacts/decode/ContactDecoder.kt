package com.d.microsoft.contacts.decode

import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logError
import com.d.microsoft.contacts.bean.Contact
import org.json.JSONArray
import org.json.JSONObject

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/7
 * @description:
 * a decoder for convert json to entity class Contact
 * Gson or FastJson is better choice
 */
class ContactDecoder : IContactDecoder, Logger {
    override fun jsonToContacts(json: String): List<Contact>? {
        return try {
            jsonToContacts(JSONArray(json))
        } catch (e: Exception) {
            logError { "jsonToContacts error msg: ${e.message}" }
            null
        }
    }

    override fun jsonToContacts(jsonArray: JSONArray): List<Contact>? {
        val list = mutableListOf<Contact>()
        try {
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val contact = jsonToContact(jsonObject)
                contact?.let { list.add(it) }
            }
        } catch (e: Exception) {
            logError { "jsonToContacts error msg: ${e.message}" }
        }
        return list
    }

    override fun jsonToContact(json: String): Contact? {
        return try {
            jsonToContact(JSONObject(json))
        } catch (e: Exception) {
            logError { "jsonToContacts error msg: ${e.message}" }
            null
        }
    }

    override fun jsonToContact(jsonObject: JSONObject): Contact? {
        return try {
            // this analysis is not great, we can use Gson or FastJson instead
            val firstName = jsonObject.getString("first_name")
            val lastName = jsonObject.getString("last_name")
            val avatarFileName = jsonObject.getString("avatar_filename")
            val title = jsonObject.getString("title")
            val introduction = jsonObject.getString("introduction")
            Contact(firstName, lastName, avatarFileName, title, introduction)
        } catch (e: Exception) {
            logError { "jsonToContacts error msg: ${e.message}" }
            null
        }
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "ContactDecoder"
    }
}