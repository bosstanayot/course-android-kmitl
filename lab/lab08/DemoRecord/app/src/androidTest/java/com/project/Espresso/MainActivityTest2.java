package com.project.Espresso;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void TestCase1() {
        /*
        input Name = null, Age = null then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText(""), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText(""), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
    }
    @Test
    public void TestCase2() {
        /*
        input Name = null, Age = 20 then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText(""), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase3() {
        /*
        input Name = null, Age = null then press "GO TO LIST" button
        if true view will change to new view and have only one text and text is "Not Found"
        */
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Not Found")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase4() {
        /*
        input Name = Ying, Age = null then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText(""), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase5() {
        /*
        input Name = Ying, Age = 20 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Ying\nAge : 20" on 1st listview"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge)).check(matches(withText("20")));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase6() {
        /*
        input Name = Ladarat, Age = 20 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Ladarat\nAge : 20" on 2nd listview"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge)).check(matches(withText("20")));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase7() {
        /*
        input Name = Somkait, Age = 80 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Somkait\nAge : 80" on 3rd listview"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge)).check(matches(withText("80")));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase8() {
        /*
        input Name = Prayoch, Age = 60 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Prayoch\nAge : 60" on 4th listview"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge)).check(matches(withText("60")));
        SystemClock.sleep(1000);
    }

    @Test
    public void TestCase9() {
        /*
        input Name = Prayoch, Age = 50 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Prayoch\nAge : 50" on 5th listview"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName)).check(matches(withText("Prayoch")));
        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge)).check(matches(withText("50")));
        SystemClock.sleep(1000);
    }
}