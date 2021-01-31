package com.jake.bucketplace.snsapp.data.repository.card

import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User
import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.network.CardServiceApi
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CardRepositoryImplTest {

    private lateinit var cardRepository: CardRepositoryImpl

    @MockK
    private lateinit var cardServiceApi: CardServiceApi
    private val scheduler = Schedulers.trampoline()

    @MockK
    private lateinit var cardMapper: CardMapper

    @MockK
    private lateinit var cardDetailMapper: CardDetailMapper

    private val testUserId: Long = 2
    private val testUserNickName = "TEST_NICKNAME"
    private val testUserIntroduction = "TEST_INTRO"
    private val testCardId: Long = 1
    private val testCardDescription = "TEST_DESC"
    private val testCardImageUrl = "TEST_URL"

    @MockK
    private lateinit var dataUser: User
    @MockK
    private lateinit var dataCard: Card
    @MockK
    private lateinit var domainUser: com.jake.bucketplace.snsapp.domain.model.User
    @MockK
    private lateinit var domainCard: com.jake.bucketplace.snsapp.domain.model.Card
    @MockK
    private lateinit var domainCardDetail: CardDetail

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cardRepository = CardRepositoryImpl(cardServiceApi, scheduler, cardMapper, cardDetailMapper)
    }

    @Test
    fun getCards_success() {
        val inputPage = 2
        cardRepository.page = inputPage

        val cardResponse = CardResponse(true, listOf(dataCard), "")
        val response = Response.success(cardResponse)

        val expectedCards = listOf(domainCard)

        every {
            cardServiceApi.getCards(
                inputPage,
                CardRepositoryImpl.DEFAUALT_PER
            )
        } returns Single.just(response)
        every { cardMapper.transform(dataCard) } returns domainCard

        cardRepository.getCards(false).test()
            .assertNoErrors()
            .assertValue { it == expectedCards }

        verify(ordering = Ordering.SEQUENCE) {
            cardServiceApi.getCards(inputPage, CardRepositoryImpl.DEFAUALT_PER)
            cardMapper.transform(dataCard)
        }

        val expectedTargetPage = inputPage + 1

        val actualTargetPage = cardRepository.page
        Assert.assertEquals(expectedTargetPage, actualTargetPage)
    }

    @Test
    fun getCards_forceUpdate_success() {
        val inputPage = 10
        cardRepository.page = inputPage

        val cardResponse = CardResponse(true, listOf(dataCard), "")
        val response = Response.success(cardResponse)

        val expectedCards = listOf(domainCard)

        val expectedTargetPage = CardRepositoryImpl.DEFAUALT_PAGE_INDEX + 1

        every {
            cardServiceApi.getCards(
                CardRepositoryImpl.DEFAUALT_PAGE_INDEX,
                CardRepositoryImpl.DEFAUALT_PER
            )
        } returns Single.just(response)
        every { cardMapper.transform(dataCard) } returns domainCard

        cardRepository.getCards(true).test()
            .assertNoErrors()
            .assertValue { it == expectedCards }

        verify(ordering = Ordering.SEQUENCE) {
            cardServiceApi.getCards(
                CardRepositoryImpl.DEFAUALT_PAGE_INDEX,
                CardRepositoryImpl.DEFAUALT_PER
            )
            cardMapper.transform(dataCard)
        }

        val actualTargetPage = cardRepository.page
        Assert.assertEquals(expectedTargetPage, actualTargetPage)
    }

    @Test
    fun getCards_failure_status_false() {
        val inputPage = 3
        cardRepository.page = inputPage

        val errorMessge = "FAIL_TO_GET_CARDS_ERROR"
        val cardResponse = CardResponse(false, listOf(), errorMessge)
        val response = Response.success(cardResponse)

        val expectedTargetPage = inputPage

        every {
            cardServiceApi.getCards(
                inputPage,
                CardRepositoryImpl.DEFAUALT_PER
            )
        } returns Single.just(response)

        cardRepository.getCards(false).test()
            .assertErrorMessage(errorMessge)

        val actualTargetPage = cardRepository.page
        Assert.assertEquals(expectedTargetPage, actualTargetPage)
    }

    @Test
    fun getCards_failure_response_error() {
        val inputPage = 3
        cardRepository.page = inputPage

        val response = Response.error<CardResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        val expectedTargetPage = inputPage

        every {
            cardServiceApi.getCards(
                inputPage,
                CardRepositoryImpl.DEFAUALT_PER
            )
        } returns Single.just(response)

        cardRepository.getCards(false).test()
            .assertError(Throwable::class.java)

        val actualTargetPage = cardRepository.page
        Assert.assertEquals(expectedTargetPage, actualTargetPage)
    }

    @Test
    fun testGetCard_success() {
        val testRecommendCards = listOf(dataCard)
        val cardResponse = CardDetailResponse(true, dataCard, dataUser, testRecommendCards, "")
        val response = Response.success(cardResponse)

        val expectedCardDetail = domainCardDetail

        every { cardServiceApi.getCard(testCardId) } returns Single.just(response)
        every { cardDetailMapper.transform(cardResponse) } returns domainCardDetail

        cardRepository.getCard(testCardId).test()
            .assertNoErrors()
            .assertValue { it == expectedCardDetail }

        verify(ordering = Ordering.SEQUENCE) {
            cardServiceApi.getCard(testCardId)
            cardDetailMapper.transform(cardResponse)
        }
    }

    @Test
    fun testGetCard_failure_status_false() {
        val testRecommendCards = listOf(dataCard)
        val errorMessage = "FAILURE_TO_GET_CARD_DETAIL"
        val cardResponse = CardDetailResponse(false, dataCard, dataUser, testRecommendCards, errorMessage)
        val response = Response.success(cardResponse)

        every { cardServiceApi.getCard(testCardId) } returns Single.just(response)

        cardRepository.getCard(testCardId).test()
            .assertErrorMessage(errorMessage)

        verify(ordering = Ordering.SEQUENCE) {
            cardServiceApi.getCard(testCardId)
        }
    }

    @Test
    fun testGetCard_failure_response_error() {
        val response = Response.error<CardDetailResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        every { cardServiceApi.getCard(testCardId) } returns Single.just(response)

        cardRepository.getCard(testCardId).test()
            .assertError(Throwable::class.java)

        verify(ordering = Ordering.SEQUENCE) {
            cardServiceApi.getCard(testCardId)
        }
    }

}