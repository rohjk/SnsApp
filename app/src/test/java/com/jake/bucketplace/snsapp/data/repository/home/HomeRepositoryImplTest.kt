package com.jake.bucketplace.snsapp.data.repository.home

import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User
import com.jake.bucketplace.snsapp.data.network.HomeServiceApi
import com.jake.bucketplace.snsapp.data.repository.card.CardDetailResponse
import com.jake.bucketplace.snsapp.domain.model.Home
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Response

class HomeRepositoryImplTest {

    private lateinit var homeRepository: HomeRepositoryImpl

    @MockK
    private lateinit var homeServiceApi: HomeServiceApi
    private val scheduler = Schedulers.trampoline()
    @MockK
    private lateinit var homeMapper: HomeMapper

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
        homeRepository = HomeRepositoryImpl(homeServiceApi,scheduler,homeMapper)
    }

    @Test
    fun getHome_success() {
        val homeResponse = HomeResponse (
            true,
            listOf(dataCard),
            listOf(dataUser),
            ""
        )
        val response = Response.success(homeResponse)

        val home = Home(
            listOf(domainCard),
            listOf(domainUser)
        )

        every { homeServiceApi.getHome() } returns Single.just(response)
        every { homeMapper.transform(homeResponse) } returns home

        homeRepository.getHome().test()
            .assertNoErrors()
            .assertValue { it == home }

        verify {
            homeServiceApi.getHome()
            homeMapper.transform(homeResponse)
        }
    }

    @Test
    fun getHome_failire_status_false() {
        val errorMessage = "FAILURE_TO_GET_HOME"
        val homeResponse = HomeResponse (
            false,
            listOf(dataCard),
            listOf(dataUser),
            errorMessage
        )
        val response = Response.success(homeResponse)

        every { homeServiceApi.getHome() } returns Single.just(response)

        homeRepository.getHome().test()
            .assertErrorMessage(errorMessage)

        verify {
            homeServiceApi.getHome()
        }
    }

    @Test
    fun getHome_failire_response_error() {
        val response = Response.error<HomeResponse>(
            400,
            ResponseBody.create(
                MediaType.get("application/json; charset=UTF-8"),
                "[text={\"message\":\"record not found\"}\\n]"
            )
        )

        every { homeServiceApi.getHome() } returns Single.just(response)

        homeRepository.getHome().test()
            .assertError(Throwable::class.java)

        verify {
            homeServiceApi.getHome()
        }
    }
}