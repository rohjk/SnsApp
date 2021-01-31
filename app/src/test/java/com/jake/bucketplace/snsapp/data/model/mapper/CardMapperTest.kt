package com.jake.bucketplace.snsapp.data.model.mapper

import com.jake.bucketplace.snsapp.data.model.Card
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CardMapperTest {

    private val cardMapper = CardMapper()

    private val testCardId: Long = 1
    private val testUserId: Long = 2
    private val testDescription: String = "TEST_DESC"
    private val testImageUrl: String = "TEST_IMAGE_URL"

    @Before
    fun setUp() {

    }

    @Test
    fun testTransform_success() {
        val inputDataCard = Card(
            id = testCardId,
            userId = testUserId,
            description = testDescription,
            imageUrl = testImageUrl
        )

        val expectedDomainCard = com.jake.bucketplace.snsapp.domain.model.Card (
            id = testCardId,
            userId = testUserId,
            description = testDescription,
            imageUrl = testImageUrl
        )

        val actual = cardMapper.transform(inputDataCard)

        Assert.assertEquals(expectedDomainCard, actual)
    }
}