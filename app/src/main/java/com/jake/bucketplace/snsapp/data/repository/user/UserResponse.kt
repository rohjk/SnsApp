package com.jake.bucketplace.snsapp.data.repository.user

import com.google.gson.annotations.SerializedName
import com.jake.bucketplace.snsapp.data.model.User

data class UserResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("user")
    val user: User,
    @SerializedName("msg")
    val errorMessage: String
)