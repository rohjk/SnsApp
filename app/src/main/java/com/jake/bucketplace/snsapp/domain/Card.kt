package com.jake.bucketplace.snsapp.domain

data class Card(
    val id: Long,
    val userId: Long,
    val description: String,
    val imageUrl : String
)