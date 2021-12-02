package com.example.cardinfofinder.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cardinfofinder.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CardInfoActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<CardInfoActivity> = ActivityScenarioRule(CardInfoActivity::class.java)

    private var dialog: BottomSheetDialog? = null

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_card_number_edit_text_in_view() {
        onView(ViewMatchers.withId(R.id.card_number_edit_text))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_that_user_can_enter_input() {
        onView(ViewMatchers.withId(R.id.card_number_edit_text)).perform(typeText("41874515"),
            closeSoftKeyboard()).perform(click())
    }

    @Test
    fun test_button_visibility_name(){
        onView(ViewMatchers.withId(R.id.check_button))
            .check(matches(isDisplayed()))
    }

}

