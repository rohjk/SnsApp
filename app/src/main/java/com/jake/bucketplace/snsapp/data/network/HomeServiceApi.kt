package com.jake.bucketplace.snsapp.data.network

import com.jake.bucketplace.snsapp.data.repository.HomeResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET

interface HomeServiceApi {

    @GET("home/")
    fun getHome(): Flowable<Response<HomeResponse>>

}