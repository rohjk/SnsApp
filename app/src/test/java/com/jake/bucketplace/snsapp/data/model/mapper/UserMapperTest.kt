package com.jake.bucketplace.snsapp.data.model.mapper

import com.jake.bucketplace.snsapp.data.model.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private val userMapper = UserMapper()

    private val testUserId: Long = 1
    private val testNickname: String = "TEST_NICKNAME"
    private val testIntroduction: String = "TEST_INTRODUCTION"

    @Before
    fun setUp() {

    }

    @Test
    fun testTransform_success() {

        val inputDataUser = User(
            id = testUserId,
            nickName = testNickname,
            introduction = testIntroduction
        )

        val expectedResult = com.jake.bucketplace.snsapp.domain.model.User(
            id = testUserId,
            nickName = testNickname,
            introduction = testIntroduction
        )

        val actual = userMapper.transform(inputDataUser)

        Assert.assertEquals(expectedResult, actual)
    }


}