package com.d.microsoft.contacts

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.d.microsoft.R
import org.hamcrest.Matchers.endsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author: yaobeihaoyu
 * @version: 1.0
 * @since: 2022/3/9
 * @description: ui tests about AvatarTagLayout and detail ViewPager2
 * The fixed number of contacts is 28.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ContactActivityTest {
    @Rule
    fun activityRule() = ActivityScenarioRule(ContactActivity::class.java)

    /**
     * test scroll AvatarTagLayout
     */
    @Test
    fun testScrollAvatarTagLayout() {
        onView(withId(R.id.rv_avatar))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(END_POSITION))
    }

    /**
     * test scroll AvatarTagLayout to start edge
     */
    @Test
    fun testScrollToStartAvatarTagLayout() {
        onView(withId(R.id.rv_avatar))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(START_EDGE))
    }

    /**
     * test scroll AvatarTagLayout to end edge
     */
    @Test
    fun testScrollToEndAvatarTagLayout() {
        onView(withId(R.id.rv_avatar))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(CONTACTS_SIZE + PLACEHOLDER_SIZE))
    }

    /**
     * test click AvatarTagLayout item
     */
    @Test
    fun testClickAvatarTagLayout() {
        onView(withId(R.id.rv_avatar))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    END_POSITION,
                    click()
                )
            )
    }

    /**
     * can't click outside position
     */
    @Test(expected = PerformException::class)
    fun testClickAtEdgeAvatarTagLayout() {
        onView(withId(R.id.rv_avatar))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    CONTACTS_SIZE + PLACEHOLDER_SIZE,
                    click()
                )
            )
    }

    /**
     * test scroll DetailViewPager
     */
    @Test
    fun testScrollDetailViewPager() {
        onView(withClassName(endsWith("RecyclerViewImpl")))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(END_POSITION))
    }

    /**
     * test scroll DetailViewPager to start edge
     */
    @Test
    fun testScrollToStartDetailViewPager() {
        onView(withClassName(endsWith("RecyclerViewImpl")))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(START_EDGE))
    }

    /**
     * test scroll DetailViewPager to end edge
     */
    @Test
    fun testScrollToEndDetailViewPager() {
        onView(withClassName(endsWith("RecyclerViewImpl")))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(END_EDGE))
    }

    companion object {
        private const val END_POSITION = 27
        private const val CONTACTS_SIZE = 28
        private const val PLACEHOLDER_SIZE = 2
        private const val START_EDGE = -1
        private const val END_EDGE = 28
    }
}