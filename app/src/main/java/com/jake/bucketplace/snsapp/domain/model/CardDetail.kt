package com.jake.bucketplace.snsapp.domain.model

data class CardDetail (
    val card: Card,
    val user: User,
    val recomendCards: List<Card>
)