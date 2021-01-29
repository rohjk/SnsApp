package com.jake.bucketplace.snsapp.domain.model

data class Card(
    val id: Long,
    val userId: Long,
    val description: String,
    val imageUrl : String
)