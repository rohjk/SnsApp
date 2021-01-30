package com.jake.bucketplace.snsapp.data.repository.card

import com.google.gson.annotations.SerializedName
import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User

data class CardDeatilResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("card")
    val card: Card,
    @SerializedName("user")
    val user: User,
    @SerializedName("recommend_cards")
    val recommendCards: List<Card>,
    @SerializedName("error_msg")
    val errorMessage: String
)