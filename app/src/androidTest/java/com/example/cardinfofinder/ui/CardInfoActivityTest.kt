package com.example.cardinfofinder.ui

import android.app.Dialog
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cardinfofinder.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.appcompat.widget.AppCompatTextView
import androidx.test.ext.junit.rules.activityScenarioRule
import java.lang.StringBuilder


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CardInfoActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<CardInfoActivity> =
        ActivityScenarioRule(CardInfoActivity::class.java)

    private var dialog: BottomSheetDialog? = null

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_card_number_edit_text_in_view() {
        activityScenarioRule.scenario
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_that_user_can_enter_input() {
        Espresso.onView(ViewMatchers.withId(R.id.card_number_edit_text)).perform(typeText("41874515"))
    }

    @Test
    fun test_button_visibility_name(){
        Espresso.onView(ViewMatchers.withId(R.id.check_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}

