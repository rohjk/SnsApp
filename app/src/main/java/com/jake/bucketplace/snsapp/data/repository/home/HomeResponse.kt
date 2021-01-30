package com.jake.bucketplace.snsapp.data.repository.home

import com.google.gson.annotations.SerializedName
import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User

data class HomeResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("popular_cards")
    val popularCards: List<Card>,
    @SerializedName("popular_users")
    val popularUsers: List<User>
)