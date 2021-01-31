package com.jake.bucketplace.snsapp.signin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jake.bucketplace.snsapp.auth.AuthManager
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var signInViewModel: SignInViewModel

    @MockK
    lateinit var authManager: AuthManager

    @MockK
    lateinit var isSignIn: LiveData<Boolean>

    private val disposable = CompositeDisposable()

    private val nickName = "NICK"
    private val password = "PWD"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        signInViewModel = SignInViewModel(authManager, disposable)
        every { authManager.isSignIn } returns isSignIn
    }

    @Test
    fun testSignIn_success() {
        val expectedIsSignIn = true
        val expectedIsLoading = false

        every { authManager.signIn(nickName, password) } returns Completable.complete()
        every { isSignIn.value } returns true

        signInViewModel.signIn(nickName, password)

        val actualIsSignIn = signInViewModel.isSignIn.value
        val actualIsLoading = signInViewModel.isLoading.value

        assertEquals(expectedIsSignIn, actualIsSignIn)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

    @Test
    fun testSignIn_failure() {
        val errorMessage = "FAILURE_TO_SIGN_IN"
        val expectedOnError = errorMessage
        val expectedIsSignIn = false
        val expectedIsLoading = false

        every { authManager.signIn(nickName, password) } returns Completable.error(Throwable(errorMessage))
        every { isSignIn.value } returns false

        signInViewModel.signIn(nickName, password)

        val actualOnError = signInViewModel.onError.value
        val actualIsSignIn = signInViewModel.isSignIn.value
        val actualIsLoading = signInViewModel.isLoading.value

        assertEquals(expectedOnError, actualOnError)
        assertEquals(expectedIsSignIn, actualIsSignIn)
        assertEquals(expectedIsLoading, actualIsLoading)
    }
}