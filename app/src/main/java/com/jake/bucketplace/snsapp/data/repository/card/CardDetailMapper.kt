package com.jake.bucketplace.snsapp.data.repository.card

import com.jake.bucketplace.snsapp.data.model.mapper.CardMapper
import com.jake.bucketplace.snsapp.data.model.mapper.UserMapper
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import javax.inject.Inject

class CardDetailMapper @Inject constructor(
    private val cardMapper: CardMapper,
    private val userMapper: UserMapper
) {
    fun transform(cardDetailResponse: CardDeatilResponse): CardDetail =
        with(cardDetailResponse) {
            return CardDetail(
                card = cardMapper.transform(cardDetailResponse.card),
                user = userMapper.transform(cardDetailResponse.user),
                recomendCards = cardDetailResponse.recommendCards.map { cardMapper.transform(it) }
            )
        }
}