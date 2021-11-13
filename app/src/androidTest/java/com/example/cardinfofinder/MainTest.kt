package com.example.cardinfofinder

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.cardinfofinder.ui.CardInfoActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class MainTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @get:Rule
    val activityScenario = ActivityScenarioRule(CardInfoActivity::class.java)


    @Test
    fun test_card_number_edit_text_in_view() {
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}