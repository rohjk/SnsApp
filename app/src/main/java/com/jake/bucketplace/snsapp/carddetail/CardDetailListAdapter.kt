package com.jake.bucketplace.snsapp.carddetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.CardDetail
import com.jake.bucketplace.snsapp.domain.model.User

import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_IMAGE_DETAIL = 0
private const val ITEM_VIEW_TYPE_USER_DETAIL = 1
private const val ITEM_VIEW_TYPE_RECOMMEND_CARDS = 2

class CardDetailListAdapter constructor(
    private var items: List<DataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun sumbitCardDetail(cardDetail: CardDetail) {
        val itemList: MutableList<DataItem> = mutableListOf()
        itemList.add(DataItem.CardItem(cardDetail.card))
        itemList.add(DataItem.UserItem(cardDetail.user))
        itemList.add(DataItem.RecomendCardsItem(cardDetail.recomendCards))
        this.items = itemList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.CardItem -> ITEM_VIEW_TYPE_IMAGE_DETAIL
            is DataItem.UserItem -> ITEM_VIEW_TYPE_USER_DETAIL
            is DataItem.RecomendCardsItem -> ITEM_VIEW_TYPE_RECOMMEND_CARDS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_IMAGE_DETAIL -> CardImageDetailViewHolder.from(parent)
            ITEM_VIEW_TYPE_USER_DETAIL -> CardUserDetailViewHolder.from(parent)
            ITEM_VIEW_TYPE_RECOMMEND_CARDS -> RecomendCardsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardImageDetailViewHolder -> {
                val cards = items[position] as DataItem.CardItem
                holder.bind(cards.card)
            }
            is CardUserDetailViewHolder -> {
                val users = items[position] as DataItem.UserItem
                holder.bind(users.user)
            }
            is RecomendCardsViewHolder -> {
                val recomandCards = items[position] as DataItem.RecomendCardsItem
                holder.bind(recomandCards.cards)
            }
        }
    }

}

sealed class DataItem {

    data class CardItem(val card: Card) : DataItem() {
        override val id = card.hashCode().toLong()
    }

    data class UserItem(val user: User) : DataItem() {
        override val id = user.hashCode().toLong()
    }

    data class RecomendCardsItem(val cards: List<Card>) : DataItem() {
        override val id = cards.hashCode().toLong()
    }

    abstract val id: Long
}
