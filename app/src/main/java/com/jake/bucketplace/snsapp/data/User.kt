package com.jake.bucketplace.snsapp.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("nickname")
    val nickName: String,
    @SerializedName("introduction")
    val introduction: String
)