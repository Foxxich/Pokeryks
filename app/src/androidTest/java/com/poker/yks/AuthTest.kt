package com.example.datingapp

import com.example.datingapp.TestData.Companion.CORRECT_EMAIL
import com.example.datingapp.TestData.Companion.CORRECT_PASSWORD
import com.example.datingapp.TestData.Companion.INCORRECT_EMAIL
import com.example.datingapp.TestData.Companion.INCORRECT_PASSWORD
import com.example.datingapp.firebase.FirebaseAuthController
import com.example.datingapp.firebase.FirebaseAuthControllerImpl
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

@HiltAndroidTest
class FirebaseAuthControllerTest {

    private var firebaseAuthController: FirebaseAuthController = FirebaseAuthControllerImpl()

    @Test
    fun userIsRegistered() {
        firebaseAuthController.logout()
        runBlocking {
            Assert.assertNotNull(
                firebaseAuthController.isCurrentUserRegistered(
                    CORRECT_EMAIL,
                    CORRECT_PASSWORD
                )?.user
            )
        }
    }

    @Test
    fun userIsNotRegistered() {
        firebaseAuthController.logout()
        Assert.assertThrows(FirebaseAuthInvalidCredentialsException::class.java) {
            runBlocking {
                firebaseAuthController.isCurrentUserRegistered(
                    CORRECT_EMAIL,
                    INCORRECT_PASSWORD
                )?.user
            }
        }
    }

    @Test
    fun emailExists() {
        firebaseAuthController.logout()
        runBlocking {
            Assert.assertEquals(firebaseAuthController.emailExists(CORRECT_EMAIL), true)
        }
    }

    @Test
    fun emailNotExists() {
        firebaseAuthController.logout()
        runBlocking {
            Assert.assertEquals(firebaseAuthController.emailExists(INCORRECT_EMAIL), false)
        }
    }

    @Test
    fun userIsNotSigned() {
        firebaseAuthController.logout()
        runBlocking {
            Assert.assertEquals(firebaseAuthController.isCurrentUserSigned(), false)
        }
    }
}