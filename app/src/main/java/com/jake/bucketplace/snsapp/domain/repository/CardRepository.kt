package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.model.Card
import io.reactivex.Single

interface CardRepository {
    fun getCards(foreUpdate: Boolean): Single<List<Card>>
}