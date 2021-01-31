package com.jake.bucketplace.snsapp.data.repository.home

import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User
import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.domain.model.Home
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class HomeMapperTest {

    lateinit var homeMapper: HomeMapper

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
        homeMapper = HomeMapper(cardMapper, userMapper)
    }

    @Test
    fun testTransform() {
        val homeResponse = HomeResponse(
            true,
            listOf(dataCard),
            listOf(dataUser),
            ""
        )

        every { userMapper.transform(dataUser) } returns domainUser
        every { cardMapper.transform(dataCard) } returns domainCard

        val expectedResult = Home(
            listOf(domainCard),
            listOf(domainUser)
        )

        val actual = homeMapper.transform(homeResponse)

        assertEquals(expectedResult, actual)

        verify {
            cardMapper.transform(dataCard)
            userMapper.transform(dataUser)
        }
    }
}