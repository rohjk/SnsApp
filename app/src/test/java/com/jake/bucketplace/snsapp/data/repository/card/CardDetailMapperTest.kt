package com.jake.bucketplace.snsapp.data.repository.card

import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User
import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CardDetailMapperTest {

    lateinit var cardDetailMapper: CardDetailMapper

    @MockK
    lateinit var cardMapper: CardMapper

    @MockK
    lateinit var userMapper: UserMapper

    val inputUser = User(
        id = 1,
        nickName = "TEST_NICKNAME",
        introduction = "TEST_INTRODUCTION"
    )

    val inputCard = Card(
        id = 1,
        userId = inputUser.id,
        description = "TEST_DESC",
        imageUrl = "TEST_IMAGE_URL"
    )

    val inputRecommendCard1 = Card(
        id = 2,
        userId = inputUser.id,
        description = "TEST_DESC2",
        imageUrl = "TEST_IMAGE_URL2"
    )

    val inputRecommendCard2 = Card(
        id = 3,
        userId = inputUser.id,
        description = "TEST_DESC3",
        imageUrl = "TEST_IMAGE_URL3"
    )

    val inputRecommendCards = listOf(inputRecommendCard1, inputRecommendCard2)

    val expectedUser = com.jake.bucketplace.snsapp.domain.model.User(1, "NickName", "Intro")
    val expectedCard =
        com.jake.bucketplace.snsapp.domain.model.Card(1, expectedUser.id, "DES1", "URL1")
    val expectedRecommendCard1 =
        com.jake.bucketplace.snsapp.domain.model.Card(2, expectedUser.id, "DESC2", "URL2")
    val expectedRecommendCard2 =
        com.jake.bucketplace.snsapp.domain.model.Card(3, expectedUser.id, "DESC3", "URL3")

    val expectedRecommendCars = listOf(expectedRecommendCard1, expectedRecommendCard2)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cardDetailMapper = CardDetailMapper(cardMapper, userMapper)
    }

    @Test
    fun testTransform_success() {
        val inputCardDeatilResponse = CardDeatilResponse(
            true,
            inputCard,
            inputUser,
            inputRecommendCards,
            ""
        )

        val expectedResult = CardDetail(
            expectedCard,
            expectedUser,
            expectedRecommendCars
        )

        every { userMapper.transform(inputUser) } returns expectedUser
        every { cardMapper.transform(inputCard) } returns expectedCard
        every { cardMapper.transform(inputRecommendCard1) } returns expectedRecommendCard1
        every { cardMapper.transform(inputRecommendCard2) } returns expectedRecommendCard2

        val actual = cardDetailMapper.transform(inputCardDeatilResponse)

        verify {
            cardMapper.transform(inputCard)
            userMapper.transform(inputUser)
            cardMapper.transform(inputRecommendCard1)
            cardMapper.transform(inputRecommendCard2)
        }

        Assert.assertEquals(expectedResult, actual)
    }

    @Test
    fun testTransform_empty_recommend_cards_success() {
        val inputCardDeatilResponse = CardDeatilResponse(
            true,
            inputCard,
            inputUser,
            emptyList(),
            ""
        )

        val expectedResult = CardDetail(
            expectedCard,
            expectedUser,
            emptyList()
        )

        every { userMapper.transform(inputUser) } returns expectedUser
        every { cardMapper.transform(inputCard) } returns expectedCard

        val actual = cardDetailMapper.transform(inputCardDeatilResponse)

        verify {
            cardMapper.transform(inputCard)
            userMapper.transform(inputUser)
        }

        Assert.assertEquals(expectedResult, actual)
    }

}