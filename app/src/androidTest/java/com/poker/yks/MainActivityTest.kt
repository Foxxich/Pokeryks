package com.example.datingapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.datingapp.TestData.Companion.CORRECT_EMAIL
import com.example.datingapp.TestData.Companion.CORRECT_PASSWORD
import com.example.datingapp.activities.MainActivity
import com.example.datingapp.firebase.FirebaseAuthController
import com.example.datingapp.firebase.FirebaseAuthControllerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val firebaseAuthController: FirebaseAuthController = FirebaseAuthControllerImpl()

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>().apply {
        runBlocking {
            firebaseAuthController.isCurrentUserRegistered(CORRECT_EMAIL, CORRECT_PASSWORD)
        }
    }

    @Test
    fun existingUI() {
        composeTestRule.onNodeWithTag("profile_tag").assertExists()
        composeTestRule.onNodeWithTag("home", true).assertExists()
        runBlocking {
            composeTestRule.onNodeWithTag("profile", true).performClick()
            withContext(Dispatchers.Main) {
                delay(5000) // Adjust the delay as per your requirements
            }
        }
//        firebaseAuthController.logout()
    }
}