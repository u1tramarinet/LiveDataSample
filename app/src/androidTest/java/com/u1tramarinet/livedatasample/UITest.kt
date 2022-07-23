package com.u1tramarinet.livedatasample

import android.os.SystemClock
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {
    @Rule
    var scenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun initialDisplay() {
        scenarioRule.scenario.use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        }
    }

    @Test
    fun countUp() {
        scenarioRule.scenario.use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)

            // first click(can change, off course)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("11")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("1")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("111")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))

            // up to upper limit(can change)
            performActionRepeatedly(R.id.first_digit_up, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("119")))
            performActionRepeatedly(R.id.second_digit_up, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("199")))
            performActionRepeatedly(R.id.third_digit_up, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("999")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))

            // already upper limit(can't change)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("999")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("999")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("9")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("999")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        }
    }

    @Test
    fun countDown() {
        scenarioRule.scenario.use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)

            // move to upper limit(prepare)
            performActionRepeatedly(R.id.first_digit_up, ViewActions.click(), 9)
            performActionRepeatedly(R.id.second_digit_up, ViewActions.click(), 9)
            performActionRepeatedly(R.id.third_digit_up, ViewActions.click(), 9)

            // first click(can change, off course)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("8")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("998")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("8")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("988")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("8")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("888")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))

            // up to upper limit(can change)
            performActionRepeatedly(R.id.first_digit_down, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("880")))
            performActionRepeatedly(R.id.second_digit_down, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("800")))
            performActionRepeatedly(R.id.third_digit_down, ViewActions.click(), 8)
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))

            // already upper limit(can't change)
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.first_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.second_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.third_digit)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
            Espresso.onView(ViewMatchers.withId(R.id.first_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
        }
    }

    @Test
    fun digitDisplay() {
        scenarioRule.scenario.use { scenario ->
            scenario.moveToState(Lifecycle.State.RESUMED)
            // initial check
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))

            // 1 digit -> 3 digits -> 1 digit
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("100")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))

            // 1 digit -> 2 digits -> 1 digit
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("10")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))

            // 2 digits -> 3 digits -> 2 digits
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("10")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_up)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("110")))
            Espresso.onView(ViewMatchers.withId(R.id.third_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("10")))
            Espresso.onView(ViewMatchers.withId(R.id.second_digit_down)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.result)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
        }
    }

    private fun performActionRepeatedly(@IdRes resId: Int, action: ViewAction, repeatCount: Int) {
        for (i in 0 until repeatCount) {
            Espresso.onView(ViewMatchers.withId(resId)).perform(action)
            SystemClock.sleep(500)
        }
    }
}