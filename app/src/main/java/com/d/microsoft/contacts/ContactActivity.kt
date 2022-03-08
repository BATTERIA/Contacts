package com.d.microsoft.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.d.microsoft.R
import com.d.microsoft.base.utils.Logger
import com.d.microsoft.base.utils.logInfo
import com.d.microsoft.contacts.ui.pager.DetailPageAdapter
import com.d.microsoft.contacts.ui.tab.AvatarTagAdapter
import com.d.microsoft.contacts.ui.tab.AvatarTagLayout
import com.d.microsoft.contacts.ui.tab.AvatarTagLayoutMediator

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/5
 * @description: Activity for Contacts
 */
class ContactActivity : AppCompatActivity(), Logger {
    // ViewModel
    private var contactViewModel: ContactViewModel? = null
    // ViewPager2 for detail view
    private var viewPager: ViewPager2? = null
    // custom RecyclerView for avatar view
    private var tagLayout: AvatarTagLayout? = null
    // adapter for detail ViewPager2
    private var detailPageAdapter: DetailPageAdapter? = null
    // adapter for AvatarTagLayout
    private var avatarTagAdapter: AvatarTagAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        initViews()
        initViewModel()
        initObserver()
        getContactData()
    }

    override fun onDestroy() {
        super.onDestroy()
        avatarTagAdapter?.release()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.vp_detail)
        tagLayout = findViewById(R.id.rv_avatar)

        DetailPageAdapter().let {
            detailPageAdapter = it
            viewPager?.adapter = it
        }

        AvatarTagAdapter { position ->
            // callback when avatar tag is clicked
            logInfo { "avatar tag(position: $position) is clicked" }
            viewPager?.setCurrentItem(position, true)
            tagLayout?.scrollToTab(position)
        }.let {
            avatarTagAdapter = it
            tagLayout?.adapter = it
        }

        viewPager?.let { pager ->
            tagLayout?.let {
                // mediator between AvatarTagLayout and detail ViewPager2
                AvatarTagLayoutMediator(it, pager).attach()
            }
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initViewModel() {
        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
    }

    private fun initObserver() {
        contactViewModel?.contacts?.observe(this) {
            it ?: return@observe
            // add contacts data into adapter
            logInfo { "contacts size: ${it.size}" }
            detailPageAdapter?.addContacts(it)
            avatarTagAdapter?.addContacts(it)
        }
    }

    // get contact data
    private fun getContactData() {
        contactViewModel?.getContactData()
    }

    override val logTag: String
        get() = TAG

    companion object {
        private const val TAG = "ContactActivity"
    }
}