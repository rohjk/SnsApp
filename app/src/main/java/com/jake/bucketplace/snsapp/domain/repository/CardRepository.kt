package com.jake.bucketplace.snsapp.domain.repository

import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import io.reactivex.Single

interface CardRepository {
    fun getCards(foreUpdate: Boolean): Single<List<Card>>
    fun getCard(id: Long): Single<CardDetail>
}