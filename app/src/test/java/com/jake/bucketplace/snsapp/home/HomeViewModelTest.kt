package com.jake.bucketplace.snsapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.repository.HomeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var homeViewModel: HomeViewModel

    @MockK
    lateinit var homeRepository: HomeRepository

    private val scheduler = Schedulers.trampoline()
    private val disposable = CompositeDisposable()

    @MockK
    lateinit var home: Home

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(homeRepository, scheduler, disposable)
    }

    @Test
    fun getHome_success() {
        val expectedHome = home
        val expectedIsLoading = false

        every { homeRepository.getHome() } returns Single.just(home)

        homeViewModel.loadHome()

        val actualHome = homeViewModel.home.value
        val actualIsLoading = homeViewModel.isLoading.value

        assertEquals(expectedHome, actualHome)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

    @Test
    fun getHome_failure() {
        val errorMessage = "FAILURE_TO_GET_HOME"
        val expectedOnError = errorMessage
        val expectedIsLoading = false

        every { homeRepository.getHome() } returns Single.error(Throwable(errorMessage))

        homeViewModel.loadHome()

        val actualOnError = homeViewModel.onError.value
        val actualIsLoading = homeViewModel.isLoading.value

        assertEquals(expectedOnError, actualOnError)
        assertEquals(expectedIsLoading, actualIsLoading)
    }
}