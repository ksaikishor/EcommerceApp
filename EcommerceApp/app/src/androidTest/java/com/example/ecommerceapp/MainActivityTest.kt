package com.example.ecommerceapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testOnCreate() {
        activityRule.scenario.onActivity { activity ->
            assertNotNull(activity.cartViewModel)

            val currentFragment = activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
            assertTrue(currentFragment is LoginFragment)
        }
    }

    @Test
    fun testReplaceFragment() {
        activityRule.scenario.onActivity { activity ->
            val newFragment = LoginFragment.getInstance()

            activity.replaceFragment(newFragment)

            val currentFragment = activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
            assertEquals(newFragment, currentFragment)
        }
    }


    @Test
    fun testViewModelInitializations() {
        activityRule.scenario.onActivity { activity ->
            assertNotNull(activity.cartViewModel)
        }
    }
}
