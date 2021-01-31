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

    @MockK
    lateinit var dataUser: User

    @MockK
    lateinit var dataCard: Card

    @MockK
    lateinit var domainUser: com.jake.bucketplace.snsapp.domain.model.User

    @MockK
    lateinit var domainCard: com.jake.bucketplace.snsapp.domain.model.Card

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cardDetailMapper = CardDetailMapper(cardMapper, userMapper)

        every { userMapper.transform(dataUser) } returns domainUser
        every { cardMapper.transform(dataCard) } returns domainCard
    }

    @Test
    fun testTransform_success() {
        val inputCardDeatilResponse = CardDetailResponse(
            true,
            dataCard,
            dataUser,
            listOf(dataCard),
            ""
        )

        val expectedResult = CardDetail(
            domainCard,
            domainUser,
            listOf(domainCard)
        )

        val actual = cardDetailMapper.transform(inputCardDeatilResponse)

        verify {
            cardMapper.transform(dataCard)
            userMapper.transform(dataUser)
        }

        Assert.assertEquals(expectedResult, actual)
    }

    @Test
    fun testTransform_empty_recommend_cards_success() {
        val inputCardDeatilResponse = CardDetailResponse(
            true,
            dataCard,
            dataUser,
            emptyList(),
            ""
        )

        val expectedResult = CardDetail(
            domainCard,
            domainUser,
            emptyList()
        )

        val actual = cardDetailMapper.transform(inputCardDeatilResponse)

        verify {
            cardMapper.transform(dataCard)
            userMapper.transform(dataUser)
        }

        Assert.assertEquals(expectedResult, actual)
    }

}