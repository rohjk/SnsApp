package com.jake.bucketplace.snsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jake.bucketplace.snsapp.auth.AuthManager
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

class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel

    @MockK
    lateinit var authManager: AuthManager

    @MockK
    lateinit var isSignIn: LiveData<Boolean>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(authManager)
        every { authManager.isSignIn } returns isSignIn
    }

    @Test
    fun testIsSignIn_true() {
        val expectedIsSignIn = true
        every { isSignIn.value } returns true

        val actualIsSignIn = mainViewModel.isSignIn.value

        assertEquals(expectedIsSignIn, actualIsSignIn)
    }

    @Test
    fun testIsSignIn_false() {
        val expectedIsSignIn = false
        every { isSignIn.value } returns false

        val actualIsSignIn = mainViewModel.isSignIn.value

        assertEquals(expectedIsSignIn, actualIsSignIn)
    }

    @Test
    fun testIsSignOut_success() {
        every { authManager.signOut() } returns Completable.complete()

        mainViewModel.signOut()

        verify {
            authManager.signOut()
        }
    }
}