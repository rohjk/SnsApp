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
        itemList.add(DataItem.RecommendCardsItem(cardDetail.recomendCards))
        this.items = itemList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.CardItem -> ITEM_VIEW_TYPE_IMAGE_DETAIL
            is DataItem.UserItem -> ITEM_VIEW_TYPE_USER_DETAIL
            is DataItem.RecommendCardsItem -> ITEM_VIEW_TYPE_RECOMMEND_CARDS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_IMAGE_DETAIL -> CardDetailImageDetailViewHolder.from(parent)
            ITEM_VIEW_TYPE_USER_DETAIL -> CardDetailUserDetailViewHolder.from(parent)
            ITEM_VIEW_TYPE_RECOMMEND_CARDS -> RecommendCardsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardDetailImageDetailViewHolder -> {
                val cards = items[position] as DataItem.CardItem
                holder.bind(cards.card)
            }
            is CardDetailUserDetailViewHolder -> {
                val users = items[position] as DataItem.UserItem
                holder.bind(users.user)
            }
            is RecommendCardsViewHolder -> {
                val recommendCards = items[position] as DataItem.RecommendCardsItem
                holder.bind(recommendCards.cards)
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

    data class RecommendCardsItem(val cards: List<Card>) : DataItem() {
        override val id = cards.hashCode().toLong()
    }

    abstract val id: Long
}
