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
public class MvpDesignActivityTest {

    public static final String PROVINCE = "湖南";
    public static final String CITY = "长沙";

    @Rule
    public ActivityTestRule<MvpDesignActivity> mActivityRule = new ActivityTestRule<MvpDesignActivity>(MvpDesignActivity.class);

    @Test
    public void getWeatherSuccess() {
        Espresso.onView(ViewMatchers.withId(R.id.et_province)).perform(ViewActions.replaceText(PROVINCE));
        Espresso.onView(ViewMatchers.withId(R.id.et_city)).perform(ViewActions.replaceText(CITY));
        Espresso.onView(ViewMatchers.withId(R.id.btn_submit)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.ry_weather)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

}