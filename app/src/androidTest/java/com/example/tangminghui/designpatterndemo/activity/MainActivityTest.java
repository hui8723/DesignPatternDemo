package com.example.tangminghui.designpatterndemo.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.tangminghui.designpatterndemo.R;

import org.junit.Rule;
import org.junit.Test;


/**
 * Created by hui on 2017/9/21.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void showMvcDesignActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_mvc)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.et_province)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}