package com.jake.bucketplace.snsapp.auth

import junit.framework.Assert.assertEquals
import org.junit.Test


class AuthTest {
    val auth = Auth()

    @Test
    fun testSetUserId() {
        val testUserId: Long = 123
        auth.userId = testUserId

        val expectedIsSingIn = true
        val actual = auth.isSignin

        assertEquals(expectedIsSingIn, actual)
    }

    @Test
    fun testClear() {
        val testUserId: Long = 123
        auth.userId = testUserId
        auth.clear()

        val expectedIsSingIn = false
        val actual = auth.isSignin

        assertEquals(expectedIsSingIn, actual)
    }
}