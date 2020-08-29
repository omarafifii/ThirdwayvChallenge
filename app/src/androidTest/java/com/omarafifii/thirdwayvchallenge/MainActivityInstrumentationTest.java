package com.omarafifii.thirdwayvchallenge;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);



    @Test
    public void redoClicked() {
    }

    @Test
    public void recyclerUndo() {
    }

    @Test
    public void undoClicked() {
    }

    @Test
    public void equalsBtnClicked() {
        onView(withId(R.id.ed_number)).perform(typeText("5"));
        onView(withId(R.id.bt_plus)).perform(click());
        onView(withId(R.id.bt_equals)).perform(click());
        onView(withId(R.id.tv_result)).check(matches(withText("Result = 5")));

    }
}