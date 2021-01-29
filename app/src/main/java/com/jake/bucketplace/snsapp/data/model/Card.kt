package com.jake.bucketplace.snsapp.data.model

import com.google.gson.annotations.SerializedName

data class Card (
    @SerializedName("id")
    val id: Long,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("img_url")
    val imageUrl: String
)