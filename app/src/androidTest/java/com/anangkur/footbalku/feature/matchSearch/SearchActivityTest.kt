package com.anangkur.footbalku.feature.matchSearch

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.rule.ActivityTestRule
import com.anangkur.footbalku.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SearchActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule<SearchActivity>(SearchActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun testSearchArsenal(){
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(typeText("Arsenal"))
            perform(pressImeActionButton())
        }
    }

    @Test
    fun testSearchBarcelona(){
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            perform(typeText("Barcelona"))
            perform(pressImeActionButton())
        }
    }
}