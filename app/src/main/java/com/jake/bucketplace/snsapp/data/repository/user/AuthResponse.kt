package com.jake.bucketplace.snsapp.data.repository.user

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("error_msg")
    val errorMessage: String
)