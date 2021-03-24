package com.shivamkumarjha.nasapictures

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.shivamkumarjha.nasapictures.ui.MainActivity
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
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
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpClient()
            )
        )
    }

    @Test
    fun onLaunchIsAppBarDisplayed() {
        onView(withId(R.id.appbar_id)).check(matches(isDisplayed()))
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}