package com.example.ecommerceapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @get:Rule
    //val activityRule = ActivityTestRule(MainActivity::class.java)
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testLoginWithValidCredentials() {
        onView(withId(R.id.username)).perform(typeText("testUser"))
        onView(withId(R.id.password)).perform(typeText("testPassword"))

        onView(withId(R.id.Login)).perform(click())

        onView(withId(R.id.fragment_container)).check(ViewAssertions.matches(isDisplayed()))
    }



    @Test
    fun testRegisterButtonNavigation() {
        onView(withId(R.id.Register)).perform(click())
        onView(withId(R.id.registerFragment)).check(ViewAssertions.matches(isDisplayed()))
    }
}
