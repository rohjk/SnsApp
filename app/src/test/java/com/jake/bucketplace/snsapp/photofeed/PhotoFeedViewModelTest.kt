package com.jake.bucketplace.snsapp.photofeed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PhotoFeedViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var photoFeedViewModel: PhotoFeedViewModel

    @MockK
    lateinit var cardRepository: CardRepository
    private val scheduler = Schedulers.trampoline()
    private val disposable = CompositeDisposable()

    @MockK
    lateinit var card1: Card

    @MockK
    lateinit var card2: Card

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        photoFeedViewModel = PhotoFeedViewModel(cardRepository, scheduler, disposable)
    }

    @Test
    fun loadCards_success() {
        var expectedCards = listOf(card1, card2)
        val expectedIsLoading = false
        every { cardRepository.getCards(false) } returns Single.just(expectedCards)

        photoFeedViewModel.loadCard()

        var actualCards = photoFeedViewModel.cards.value
        var actualIsLoading = photoFeedViewModel.isLoading.value

        assertEquals(expectedCards, actualCards)
        assertEquals(expectedIsLoading, actualIsLoading)

        photoFeedViewModel.loadMore()

        expectedCards = listOf(card1, card2, card1, card2)
        actualCards = photoFeedViewModel.cards.value
        actualIsLoading = photoFeedViewModel.isLoading.value

        assertEquals(expectedCards, actualCards)
        assertEquals(expectedIsLoading, actualIsLoading)

    }

    @Test
    fun loadCards_foreUpdate_success() {
        val expectedCards = listOf(card1, card2)
        val expectedIsLoading = false
        every { cardRepository.getCards(true) } returns Single.just(expectedCards)

        photoFeedViewModel.refresh()

        var actualCards = photoFeedViewModel.cards.value
        var actualIsLoading = photoFeedViewModel.isLoading.value

        assertEquals(expectedCards, actualCards)
        assertEquals(expectedIsLoading, actualIsLoading)

        photoFeedViewModel.refresh()

        actualCards = photoFeedViewModel.cards.value
        actualIsLoading = photoFeedViewModel.isLoading.value

        assertEquals(expectedCards, actualCards)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

    @Test
    fun loadCards_failure() {
        val errorMessage = "FAILIRE_TO_GET_CARDS"
        var expectedOnError = errorMessage
        val expectedIsLoading = false
        every { cardRepository.getCards(false) } returns Single.error(Throwable(errorMessage))

        photoFeedViewModel.loadCard()

        var actualOnError = photoFeedViewModel.onError.value
        var actualIsLoading = photoFeedViewModel.isLoading.value

        assertEquals(expectedOnError, actualOnError)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

}