package com.jake.bucketplace.snsapp.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class AuthManagerImplTest {

    private lateinit var authManager: AuthManagerImpl

    private val auth = Auth()

    @MockK
    private lateinit var userRepository: UserRepository

    private val scheduler = Schedulers.trampoline()

    private val testUserId: Long = 123
    private val testNickName = "NICK"
    private val testIntroduction = "INTRO"
    private val testPassword = "!@#$"

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        authManager = AuthManagerImpl(auth, userRepository, scheduler)
    }

    @Test
    fun testSignUp() {
        every { userRepository.signUp(testNickName, testIntroduction, testPassword) } returns Single.just(testUserId)

        val expectedUserId = testUserId
        val expectedSignIn = true

        authManager.signUp(testNickName, testIntroduction, testPassword).test()
            .assertNoErrors()

        verify {
            userRepository.signUp(testNickName, testIntroduction, testPassword)
        }

        val actualUserId = auth.userId
        val actualSignIn = authManager.isSignIn.value

        assertEquals(expectedUserId, actualUserId)
        assertEquals(expectedSignIn, actualSignIn)
    }

    @Test
    fun testSignIn() {
        every { userRepository.signIn(testNickName, testPassword) } returns Single.just(testUserId)

        val expectedUserId = testUserId
        val expectedSignIn = true

        authManager.signIn(testNickName, testPassword).test()
            .assertNoErrors()

        verify {
            userRepository.signIn(testNickName, testPassword)
        }

        val actualUserId = auth.userId
        val actualSignIn = authManager.isSignIn.value

        assertEquals(expectedUserId, actualUserId)
        assertEquals(expectedSignIn, actualSignIn)
    }

    @Test
    fun testSignOut() {
        auth.userId = 123

        val expectedUserId = Auth.SIGN_OUT_USER_ID
        val expectedSignIn = false

        authManager.signOut().test()
            .assertNoErrors()

        val actualUserId = auth.userId
        val actualSignIn = authManager.isSignIn.value

        assertEquals(expectedUserId, actualUserId)
        assertEquals(expectedSignIn, actualSignIn)
    }
}