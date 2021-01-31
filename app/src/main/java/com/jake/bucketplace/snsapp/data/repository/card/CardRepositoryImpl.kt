package com.jake.bucketplace.snsapp.data.repository.card

import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.network.CardServiceApi
import com.jake.bucketplace.snsapp.di.IOScheduler
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import com.jake.bucketplace.snsapp.domain.repository.CardRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class CardRepositoryImpl @Inject constructor(
    private val cardServiceApi: CardServiceApi,
    @IOScheduler private val scheduler: Scheduler,
    private val cardMapper: CardMapper,
    private val cardDetailMapper: CardDetailMapper
) : CardRepository {

    companion object {
        const val DEFAUALT_PAGE_INDEX = 1
        const val DEFAUALT_PER = 10
    }

    var page = DEFAUALT_PAGE_INDEX

    override fun getCards(foreUpdate: Boolean): Single<List<Card>> {
        if (foreUpdate) {
            return getCards()
        } else {
            return getCards(page)
        }
    }

    private fun getCards(
        page: Int = DEFAUALT_PAGE_INDEX,
        per: Int = DEFAUALT_PER
    ): Single<List<Card>> {
        return cardServiceApi.getCards(page, per).subscribeOn(scheduler).flatMap { response ->
            val cardResponse = response.body()
            if (response.isSuccessful && cardResponse != null) {
                if (cardResponse.status) {
                    pageCountUp(page)
                    val cards = cardResponse.cards.map { cardMapper.transform(it) }
                    Single.just(cards)
                } else {
                    Single.error(Throwable(cardResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_GET_CARDS_${response.code()}"))
            }
        }
    }

    private fun pageCountUp(currentPage: Int) {
        page = currentPage + 1
    }

    override fun getCard(id: Long): Single<CardDetail> {
        return cardServiceApi.getCard(id).subscribeOn(scheduler).flatMap { response ->
            val cardDetailResponse = response.body()
            if (response.isSuccessful && cardDetailResponse != null) {
                if (cardDetailResponse.status) {
                    val cardDetail = cardDetailMapper.transform(cardDetailResponse)
                    Single.just(cardDetail)
                } else {
                    Single.error(Throwable(cardDetailResponse.errorMessage))
                }
            } else {
                Single.error(Throwable("FAILURE_TO_GET_CARD_${response.code()}"))
            }
        }
    }

}