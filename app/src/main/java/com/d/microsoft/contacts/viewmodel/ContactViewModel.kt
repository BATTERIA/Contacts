package com.d.microsoft.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logInfo
import com.d.microsoft.contacts.model.bean.Contact
import com.d.microsoft.contacts.model.cache.ContactCacheManager
import com.d.microsoft.contacts.model.cache.IContactCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/5
 * @description:
 */
class ContactViewModel : ViewModel(), Logger {
    val contacts = MutableLiveData<List<Contact>>()

    private val contactCacheManager: IContactCache = ContactCacheManager()

    fun getContactData() {
        viewModelScope.launch(Dispatchers.Default) {
            logInfo { "start getting data" }
            val list = contactCacheManager.get()
            withContext(Dispatchers.Main) {
                logInfo { "got data size: ${list.size}" }
                contacts.value = list
            }
        }
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "ContactViewModel"
    }
}