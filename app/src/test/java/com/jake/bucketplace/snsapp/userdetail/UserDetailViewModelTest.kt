package com.jake.bucketplace.snsapp.userdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jake.bucketplace.snsapp.domain.model.User
import com.jake.bucketplace.snsapp.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class UserDetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var userDetailViewModel: UserDetailViewModel

    @MockK
    lateinit var userRepository: UserRepository
    private val scheduler = Schedulers.trampoline()
    private val disposable = CompositeDisposable()

    @MockK
    lateinit var user: User

    val userId: Long = 123

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userDetailViewModel = UserDetailViewModel(userRepository, scheduler, disposable)
    }

    @Test
    fun testGetUser_success() {
        val expectedUser = user
        val expectedIsLoading = false
        val expectedOnError = null

        every { userRepository.getUser(userId) } returns Single.just(user)

        userDetailViewModel.setUserId(userId)

        val actualUser = userDetailViewModel.user.value
        val actualIsLoading = userDetailViewModel.isLoading.value
        val actualOnError = userDetailViewModel.onError.value

        verify {
            userRepository.getUser(userId)
        }

        assertEquals(expectedUser, actualUser)
        assertEquals(expectedIsLoading, actualIsLoading)
        assertEquals(expectedOnError, actualOnError)
    }

    @Test
    fun testGetUser_failure() {
        val expectedUser = null
        val expectedIsLoading = false
        val errorMessage = "FAILURE_GET_USER"
        val expectedOnError = errorMessage

        every { userRepository.getUser(userId) } returns Single.error(Throwable(errorMessage))

        userDetailViewModel.setUserId(userId)

        val actualUser = userDetailViewModel.user.value
        val actualIsLoading = userDetailViewModel.isLoading.value
        val actualOnError = userDetailViewModel.onError.value

        verify {
            userRepository.getUser(userId)
        }

        assertEquals(expectedUser, actualUser)
        assertEquals(expectedIsLoading, actualIsLoading)
        assertEquals(expectedOnError, actualOnError)
    }
}