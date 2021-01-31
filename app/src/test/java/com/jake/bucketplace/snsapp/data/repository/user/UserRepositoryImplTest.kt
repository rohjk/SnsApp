package com.jake.bucketplace.snsapp.data.repository.user

import com.jake.bucketplace.snsapp.data.model.User
import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.data.network.UserServiceApi
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepositoryImpl

    @MockK
    private lateinit var userServiceApi: UserServiceApi
    private val scheduler = Schedulers.trampoline()

    @MockK
    private lateinit var userMapper: UserMapper

    @MockK
    lateinit var dataUser: User

    @MockK
    lateinit var domainUser: com.jake.bucketplace.snsapp.domain.model.User

    private val testUserId: Long = 123
    private val testNickName = "NICK"
    private val testIntroduction = "INTRO"
    private val testPassword = "!@#$"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userRepository = UserRepositoryImpl(userServiceApi, scheduler, userMapper)
    }

    @Test
    fun testGetUser_success() {
        val userResponse = UserResponse(
            true,
            dataUser,
            ""
        )
        val response = Response.success(userResponse)

        every { userServiceApi.getUser(testUserId) } returns Single.just(response)
        every { userMapper.transform(dataUser) } returns domainUser

        val expectedResult = domainUser

        userRepository.getUser(testUserId).test()
            .assertNoErrors()
            .assertValue { it == expectedResult }

        verify(ordering = Ordering.SEQUENCE) {
            userServiceApi.getUser(testUserId)
            userMapper.transform(dataUser)
        }
    }

    @Test
    fun testGetUser_failure_status_false() {
        val errorMessage = "FAILURE_TO_GET_USER"
        val userResponse = UserResponse(
            false,
            dataUser,
            errorMessage
        )
        val response = Response.success(userResponse)

        every { userServiceApi.getUser(testUserId) } returns Single.just(response)

        userRepository.getUser(testUserId).test()
            .assertErrorMessage(errorMessage)

        verify(ordering = Ordering.SEQUENCE) {
            userServiceApi.getUser(testUserId)
        }
        verify(exactly = 0) {
            userMapper.transform(any())
        }
    }

    @Test
    fun testGetUser_failure_response_error() {
        val response = Response.error<UserResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        every { userServiceApi.getUser(testUserId) } returns Single.just(response)

        userRepository.getUser(testUserId).test()
            .assertError(Throwable::class.java)

        verify(ordering = Ordering.SEQUENCE) {
            userServiceApi.getUser(testUserId)
        }
        verify(exactly = 0) {
            userMapper.transform(any())
        }
    }

    @Test
    fun testSignUp_success() {
        val authResponse = AuthResponse(
            true,
            testUserId,
            ""
        )
        val response = Response.success(authResponse)

        every { userServiceApi.signUp(testNickName, testIntroduction, testPassword) } returns Single.just(response)

        val expectedUserId = testUserId

        userRepository.signUp(testNickName, testIntroduction, testPassword).test()
            .assertNoErrors()
            .assertValue { it == expectedUserId }

        verify {
            userServiceApi.signUp(testNickName, testIntroduction, testPassword)
        }
    }

    @Test
    fun testSignUp_failure_status_false() {
        val errorMessage = "FAILURE_TO_SIGN_UP"
        val authResponse = AuthResponse(
            false,
            testUserId,
            errorMessage
        )
        val response = Response.success(authResponse)

        every { userServiceApi.signUp(testNickName, testIntroduction, testPassword) } returns Single.just(response)

        userRepository.signUp(testNickName, testIntroduction, testPassword).test()
            .assertErrorMessage(errorMessage)

        verify {
            userServiceApi.signUp(testNickName, testIntroduction, testPassword)
        }
    }

    @Test
    fun testSignUp_failure_response_error() {
        val response = Response.error<AuthResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        every { userServiceApi.signUp(testNickName, testIntroduction, testPassword) } returns Single.just(response)

        userRepository.signUp(testNickName, testIntroduction, testPassword).test()
            .assertError(Throwable::class.java)

        verify {
            userServiceApi.signUp(testNickName, testIntroduction, testPassword)
        }
    }

    @Test
    fun testSignIn() {
        val authResponse = AuthResponse(
            true,
            testUserId,
            ""
        )
        val response = Response.success(authResponse)

        every { userServiceApi.signIn(testNickName, testPassword) } returns Single.just(response)

        val expectedUserId = testUserId

        userRepository.signIn(testNickName, testPassword).test()
            .assertNoErrors()
            .assertValue { it == expectedUserId }

        verify {
            userServiceApi.signIn(testNickName, testPassword)
        }
    }

    @Test
    fun testSignIn_failure_status_false() {
        val errorMessage = "FAILURE_TO_SIGN_IN"
        val authResponse = AuthResponse(
            false,
            testUserId,
            errorMessage
        )
        val response = Response.success(authResponse)

        every { userServiceApi.signIn(testNickName, testPassword) } returns Single.just(response)

        val expectedUserId = testUserId

        userRepository.signIn(testNickName, testPassword).test()
            .assertErrorMessage(errorMessage)

        verify {
            userServiceApi.signIn(testNickName, testPassword)
        }
    }

    @Test
    fun testSignIn_failure_response_error() {
        val response = Response.error<AuthResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        every { userServiceApi.signIn(testNickName, testPassword) } returns Single.just(response)

        userRepository.signIn(testNickName, testPassword).test()
            .assertError(Throwable::class.java)

        verify {
            userServiceApi.signIn(testNickName, testPassword)
        }
    }
}