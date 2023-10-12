package com.example.ecommerceapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRegisterWithValidUserData() {
//        activityScenarioRule.scenario.onActivity(la)
       launchFragmentInContainer<RegisterFragment>()


        onView(withId(R.id.usernameReg)).perform(typeText("testUser"))
        onView(withId(R.id.passwordReg)).perform(typeText("testPassword"))
        onView(withId(R.id.email)).perform(typeText("test@example.com"))
        onView(withId(R.id.address)).perform(typeText("123 Main St"))


        onView(withId(R.id.Register)).perform(click())


        onView(withText("Registered Successfully")).check(matches(isDisplayed()))
    }

    @Test
    fun testRegisterWithInvalidUserData() {

        launchFragmentInContainer<RegisterFragment>()

        // Type invalid user data
        onView(withId(R.id.usernameReg)).perform(typeText(""))
        onView(withId(R.id.passwordReg)).perform(typeText("password"))
        onView(withId(R.id.email)).perform(typeText("invalidemail"))
        onView(withId(R.id.address)).perform(typeText("123 Main St"))

        // Click the Register button
        onView(withId(R.id.Register)).perform(click())

        // Verify that an error message is displayed
        onView(withText("please enter valid data")).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigateToLoginFragment() {
        // Launch the RegisterFragment
        launchFragmentInContainer<RegisterFragment>()

        // Click the "Login Again" button
        onView(withId(R.id.LoginAgain)).perform(click())
        launchFragmentInContainer<LoginFragment>()

        // Verify that the LoginFragment is displayed
        onView(withId(R.id.loginFragment)).check(matches(isDisplayed()))
    }
}
