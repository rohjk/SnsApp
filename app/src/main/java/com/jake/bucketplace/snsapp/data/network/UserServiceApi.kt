package com.jake.bucketplace.snsapp.data.network

import com.jake.bucketplace.snsapp.data.repository.user.AuthResponse
import com.jake.bucketplace.snsapp.data.repository.user.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface UserServiceApi {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Long): Single<Response<UserResponse>>

    @FormUrlEncoded
    @POST("users/sign_in")
    fun signIn(
        @Field("nickname") nickName: String,
        @Field("pwd") password: String
    ): Single<Response<AuthResponse>>

    @FormUrlEncoded
    @POST("users/")
    fun signUp(
        @Field("nickname") nickName: String,
        @Field("introduction") introduction: String,
        @Field("pwd") password: String
    ): Single<Response<AuthResponse>>

}