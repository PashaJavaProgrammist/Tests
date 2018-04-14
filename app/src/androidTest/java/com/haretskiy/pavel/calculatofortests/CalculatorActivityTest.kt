package com.haretskiy.pavel.calculatofortests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(CalculatorActivity::class.java)

    @Test
    fun clickNumberBottomsTest() {
//        onView(withId(R.id.name_field)).perform(typeText("Steve"));
        onView(withText("4")).perform(click())
        onView(withText("3")).perform(click())
        onView(withText("+")).perform(click())
        onView(withText("3")).perform(click())
        onView(withText("7")).perform(click())
        onView(withText("=")).perform(click())
        onView(withId(R.id.calculatorDisplay)).check(matches(ViewMatchers.withText("43 + 37 = 80.0")))
    }
}