package com.jake.bucketplace.snsapp.carddetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardDetailViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cardDetailViewModel: CardDetailViewModel

    @MockK
    lateinit var cardRepository: CardRepository

    private val scheduler = Schedulers.trampoline()
    private val disposable = CompositeDisposable()


    @MockK
    private lateinit var cardDetail: CardDetail
    private val testCardId: Long = 123

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cardDetailViewModel = CardDetailViewModel(cardRepository, scheduler, disposable)
    }

    @Test
    fun testSetCard_success() {
        val expectedCardDetail = cardDetail
        val expectedIsLoading = false

        every { cardRepository.getCard(testCardId) } returns Single.just(cardDetail)

        cardDetailViewModel.setCardId(testCardId)

        verify {
            cardRepository.getCard(testCardId)
        }

        val actualCardDetail = cardDetailViewModel.cardDetail.value
        val actualIsLoading = cardDetailViewModel.isLoading.value

        assertEquals(expectedCardDetail, actualCardDetail)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

    @Test
    fun testSetCard_failure() {
        val errorMessage = "FAILRE_TO_GET_CARD"
        val expectedOnError = errorMessage
        val expectedIsLoading = false

        every { cardRepository.getCard(testCardId) } returns Single.error(Throwable(errorMessage))

        cardDetailViewModel.setCardId(testCardId)

        verify {
            cardRepository.getCard(testCardId)
        }

        val actualOnError = cardDetailViewModel.onError.value
        val actualIsLoading = cardDetailViewModel.isLoading.value

        assertEquals(actualOnError, actualOnError)
        assertEquals(expectedIsLoading, actualIsLoading)
    }

}