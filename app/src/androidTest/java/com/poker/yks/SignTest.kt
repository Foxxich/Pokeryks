package com.example.datingapp

import android.app.Instrumentation
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.datingapp.TestData.Companion.CORRECT_EMAIL
import com.example.datingapp.TestData.Companion.CORRECT_PASSWORD
import com.example.datingapp.activities.MainActivity
import com.example.datingapp.activities.SignActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<SignActivity>()

    @Before
    fun insertTextValues() {
        composeTestRule.onNodeWithTag("email_tag").performTextInput(CORRECT_EMAIL)
        composeTestRule.onNodeWithTag("password_tag").performTextInput(CORRECT_PASSWORD)
    }

    @Test
    fun correctTextInput() {
        composeTestRule.onNodeWithTag("email_tag").assert(hasText(CORRECT_EMAIL))
    }

    @Test
    fun mainActivityOpen() {
        val activityMonitor =
            Instrumentation.ActivityMonitor(MainActivity::class.java.name, null, false)
        InstrumentationRegistry.getInstrumentation().addMonitor(activityMonitor)
        composeTestRule.onNodeWithTag("login_button_tag").performClick()
        Thread.sleep(10000L)
        Assert.assertEquals(activityMonitor.lastActivity::class, MainActivity::class)
        InstrumentationRegistry.getInstrumentation().removeMonitor(activityMonitor)
    }
}