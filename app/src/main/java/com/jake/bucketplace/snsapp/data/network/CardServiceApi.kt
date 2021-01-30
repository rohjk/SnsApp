package com.jake.bucketplace.snsapp.data.network

import com.jake.bucketplace.snsapp.data.repository.card.CardDeatilResponse
import com.jake.bucketplace.snsapp.data.repository.card.CardResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardServiceApi {

    @GET("cards")
    fun getCards(@Query("page") page: Int, @Query("per") per: Int): Single<Response<CardResponse>>

    @GET("cards/{cardId}")
    fun getCard(@Path("cardId") cardId: Long): Single<Response<CardDeatilResponse>>
}