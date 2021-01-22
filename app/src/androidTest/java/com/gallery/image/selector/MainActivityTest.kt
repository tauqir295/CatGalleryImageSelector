package com.gallery.image.selector

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.gallery.cat.ui.adapter.CatRecyclerViewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun endToEndJourneyTest() {
        onView(
            allOf(
                withId(R.id.catImageView),
                withParent(withParent(withId(R.id.container))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.launchCatJourneyBtn), withText("Choose image"), isDisplayed()
            )
        ).perform(click())
        Thread.sleep(5000)

        onView(withId(R.id.catListRv))
            .perform(scrollToPosition<CatRecyclerViewAdapter.BaseViewHolder>(5))

        onView(
            allOf(
                withId(R.id.switchButton),
                withParent(
                    allOf(
                        withId(R.id.switchContainer),
                        withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.switchButton),
                isDisplayed()
            )
        ).perform(click())

        onView(
            allOf(
                withId(R.id.switchButton),
                withParent(
                    allOf(
                        withId(R.id.switchContainer),
                        withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.catListRv)
            )
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(
            allOf(
                withId(R.id.launchCatJourneyBtn), withText("Choose image"),
                isDisplayed()
            )
        ).perform(click())

        Thread.sleep(5000)

        onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.topAppBar),
                        childAtPosition(
                            withId(R.id.appBarLayout),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        ).perform(click())

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
