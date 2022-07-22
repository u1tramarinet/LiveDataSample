package com.u1tramarinet.livedatasample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UITest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void initialDisplay() {
        try (ActivityScenario<MainActivity> scenario = scenarioRule.getScenario()){
            scenario.moveToState(Lifecycle.State.RESUMED);
            onView(withId(R.id.result)).check(matches(withText("0")));

            onView(withId(R.id.first_digit)).check(matches(withText("0")));
            onView(withId(R.id.second_digit)).check(matches(withText("0")));
            onView(withId(R.id.third_digit)).check(matches(withText("0")));

            onView(withId(R.id.first_digit_up)).check(matches(isEnabled()));
            onView(withId(R.id.second_digit_up)).check(matches(isEnabled()));
            onView(withId(R.id.third_digit_up)).check(matches(isEnabled()));

            onView(withId(R.id.first_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.second_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.third_digit_down)).check(matches(isNotEnabled()));
        }
    }

    @Test
    public void countUp() {
        try(ActivityScenario<MainActivity> scenario = scenarioRule.getScenario()) {
            scenario.moveToState(Lifecycle.State.RESUMED);

            // first click(can change, off course)
            onView(withId(R.id.first_digit_up)).perform(click());
            onView(withId(R.id.first_digit)).check(matches(withText("1")));
            onView(withId(R.id.result)).check(matches(withText("1")));

            onView(withId(R.id.second_digit_up)).perform(click());
            onView(withId(R.id.second_digit)).check(matches(withText("1")));
            onView(withId(R.id.result)).check(matches(withText("11")));

            onView(withId(R.id.third_digit_up)).perform(click());
            onView(withId(R.id.third_digit)).check(matches(withText("1")));
            onView(withId(R.id.result)).check(matches(withText("111")));

            onView(withId(R.id.first_digit_up)).check(matches(isEnabled()));
            onView(withId(R.id.second_digit_up)).check(matches(isEnabled()));
            onView(withId(R.id.third_digit_up)).check(matches(isEnabled()));

            // up to upper limit(can change)
            performActionRepeatedly(R.id.first_digit_up, click(), 8);
            onView(withId(R.id.first_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("119")));

            performActionRepeatedly(R.id.second_digit_up, click(), 8);
            onView(withId(R.id.second_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("199")));

            performActionRepeatedly(R.id.third_digit_up, click(), 8);
            onView(withId(R.id.third_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("999")));

            onView(withId(R.id.first_digit_up)).check(matches(isNotEnabled()));
            onView(withId(R.id.second_digit_up)).check(matches(isNotEnabled()));
            onView(withId(R.id.third_digit_up)).check(matches(isNotEnabled()));

            // already upper limit(can't change)
            onView(withId(R.id.first_digit_up)).perform(click());
            onView(withId(R.id.first_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("999")));

            onView(withId(R.id.second_digit_up)).perform(click());
            onView(withId(R.id.second_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("999")));

            onView(withId(R.id.third_digit_up)).perform(click());
            onView(withId(R.id.third_digit)).check(matches(withText("9")));
            onView(withId(R.id.result)).check(matches(withText("999")));

            onView(withId(R.id.first_digit_up)).check(matches(isNotEnabled()));
            onView(withId(R.id.second_digit_up)).check(matches(isNotEnabled()));
            onView(withId(R.id.third_digit_up)).check(matches(isNotEnabled()));
        }
    }

    @Test
    public void countDown() {
        try(ActivityScenario<MainActivity> scenario = scenarioRule.getScenario()) {
            scenario.moveToState(Lifecycle.State.RESUMED);

            // move to upper limit(prepare)
            performActionRepeatedly(R.id.first_digit_up, click(), 9);
            performActionRepeatedly(R.id.second_digit_up, click(), 9);
            performActionRepeatedly(R.id.third_digit_up, click(), 9);

            // first click(can change, off course)
            onView(withId(R.id.first_digit_down)).perform(click());
            onView(withId(R.id.first_digit)).check(matches(withText("8")));
            onView(withId(R.id.result)).check(matches(withText("998")));

            onView(withId(R.id.second_digit_down)).perform(click());
            onView(withId(R.id.second_digit)).check(matches(withText("8")));
            onView(withId(R.id.result)).check(matches(withText("988")));

            onView(withId(R.id.third_digit_down)).perform(click());
            onView(withId(R.id.third_digit)).check(matches(withText("8")));
            onView(withId(R.id.result)).check(matches(withText("888")));

            onView(withId(R.id.first_digit_down)).check(matches(isEnabled()));
            onView(withId(R.id.second_digit_down)).check(matches(isEnabled()));
            onView(withId(R.id.third_digit_down)).check(matches(isEnabled()));

            // up to upper limit(can change)
            performActionRepeatedly(R.id.first_digit_down, click(), 8);
            onView(withId(R.id.first_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("880")));

            performActionRepeatedly(R.id.second_digit_down, click(), 8);
            onView(withId(R.id.second_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("800")));

            performActionRepeatedly(R.id.third_digit_down, click(), 8);
            onView(withId(R.id.third_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("0")));

            onView(withId(R.id.first_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.second_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.third_digit_down)).check(matches(isNotEnabled()));

            // already upper limit(can't change)
            onView(withId(R.id.first_digit_down)).perform(click());
            onView(withId(R.id.first_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("0")));

            onView(withId(R.id.second_digit_down)).perform(click());
            onView(withId(R.id.second_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("0")));

            onView(withId(R.id.third_digit_down)).perform(click());
            onView(withId(R.id.third_digit)).check(matches(withText("0")));
            onView(withId(R.id.result)).check(matches(withText("0")));

            onView(withId(R.id.first_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.second_digit_down)).check(matches(isNotEnabled()));
            onView(withId(R.id.third_digit_down)).check(matches(isNotEnabled()));
        }
    }

    @Test
    public void digitDisplay() {
        try(ActivityScenario<MainActivity> scenario = scenarioRule.getScenario()) {
            scenario.moveToState(Lifecycle.State.RESUMED);
            // initial check
            onView(withId(R.id.result)).check(matches(withText("0")));

            // 1 digit -> 3 digits -> 1 digit
            onView(withId(R.id.third_digit_up)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("100")));

            onView(withId(R.id.third_digit_down)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("0")));

            // 1 digit -> 2 digits -> 1 digit
            onView(withId(R.id.second_digit_up)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("10")));

            onView(withId(R.id.second_digit_down)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("0")));

            // 2 digits -> 3 digits -> 2 digits
            onView(withId(R.id.second_digit_up)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("10")));

            onView(withId(R.id.third_digit_up)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("110")));

            onView(withId(R.id.third_digit_down)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("10")));

            onView(withId(R.id.second_digit_down)).perform(click());
            onView(withId(R.id.result)).check(matches(withText("0")));
        }
    }

    private void performActionRepeatedly(@IdRes int resId, @NonNull ViewAction action, int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            onView(withId(resId)).perform(action);
            SystemClock.sleep(500);
        }
    }
}
