package com.shivamkumarjha.nasapictures

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shivamkumarjha.nasapictures.ui.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        mockWebServer.start(8080)
    }

    @Test
    fun onLaunchIsAppBarDisplayed() {
        onView(withId(R.id.appbar_id)).check(matches(isDisplayed()))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}