package com.jake.bucketplace.snsapp.signup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jake.bucketplace.snsapp.auth.AuthManager
import com.jake.bucketplace.snsapp.signin.SignInViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var signUpViewModel: SignUpViewModel

    @MockK
    lateinit var authManager: AuthManager

    @MockK
    lateinit var isSignIn: LiveData<Boolean>

    private val disposable = CompositeDisposable()

    private val nickName = "NICK"
    private val introduction = "INTRO"
    private val password = "PWD"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        signUpViewModel = SignUpViewModel(authManager, disposable)
        every { authManager.isSignIn } returns isSignIn
    }

    @Test
    fun testSignUp_success() {
        val expectedIsSignIn = true
        val expectedIsLoading = false

        every { authManager.signUp(nickName, introduction, password) } returns Completable.complete()
        every { isSignIn.value } returns true

        signUpViewModel.signUp(nickName, introduction,password)

        val actualIsSignIn = signUpViewModel.isSignIn.value
        val actualIsLoading = signUpViewModel.isLoading.value

        verify {
            authManager.signUp(nickName, introduction, password)
        }

        assertEquals(expectedIsSignIn, actualIsSignIn)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

    @Test
    fun testSignUp_failure() {
        val errorMessage = "FAILURE_TO_SIGN_UP"
        val expectedOnError = errorMessage
        val expectedIsSignIn = false
        val expectedIsLoading = false

        every { authManager.signUp(nickName, introduction, password) } returns Completable.error(Throwable(errorMessage))
        every { isSignIn.value } returns false

        signUpViewModel.signUp(nickName, introduction, password)

        val actualOnError = signUpViewModel.onError.value
        val actualIsSignIn = signUpViewModel.isSignIn.value
        val actualIsLoading = signUpViewModel.isLoading.value

        verify {
            authManager.signUp(nickName, introduction, password)
        }
        assertEquals(expectedOnError, actualOnError)
        assertEquals(expectedIsSignIn, actualIsSignIn)
        assertEquals(expectedIsLoading, actualIsLoading)
    }
}