package com.jake.bucketplace.snsapp.data.network

import com.jake.bucketplace.snsapp.data.repository.user.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserServiceApi {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long): Single<Response<UserResponse>>

}