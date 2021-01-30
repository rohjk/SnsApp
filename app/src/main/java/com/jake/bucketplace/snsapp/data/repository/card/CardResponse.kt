package com.jake.bucketplace.snsapp.data.repository.card

import com.google.gson.annotations.SerializedName
import com.jake.bucketplace.snsapp.data.model.Card
import com.jake.bucketplace.snsapp.data.model.User

data class CardResponse (
    @SerializedName("ok")
    val status: Boolean,
    @SerializedName("cards")
    val cards: List<Card>
)