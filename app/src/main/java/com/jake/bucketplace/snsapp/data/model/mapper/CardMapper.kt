package com.jake.bucketplace.snsapp.data.model.mapper

import com.jake.bucketplace.snsapp.data.model.Card
import javax.inject.Inject

class CardMapper @Inject constructor() {
    fun transform(card: Card): com.jake.bucketplace.snsapp.domain.model.Card =
        with(card) {
            return com.jake.bucketplace.snsapp.domain.model.Card (
                id = id,
                userId = userId,
                description = description,
                imageUrl = imageUrl
            )
        }
}