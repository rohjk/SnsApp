package com.jake.bucketplace.snsapp.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.domain.model.Card
import com.jake.bucketplace.snsapp.domain.model.Home
import com.jake.bucketplace.snsapp.domain.model.User
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_POPULAR_CARDS = 0
private const val ITEM_VIEW_TYPE_POPULAR_USERS = 1

class HomeListAdapter constructor(
    private var items: List<DataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun sumbitHome(home: Home) {
        val itemList: MutableList<DataItem> = mutableListOf()
        itemList.add(DataItem.PopularCards(home.popularCards))
        itemList.add(DataItem.PopularUsers(home.papularUsers))
        this.items = itemList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.PopularCards -> ITEM_VIEW_TYPE_POPULAR_CARDS
            is DataItem.PopularUsers -> ITEM_VIEW_TYPE_POPULAR_USERS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_POPULAR_CARDS -> PopularCardsViewHolder.from(parent)
            ITEM_VIEW_TYPE_POPULAR_USERS -> PopularUsersViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularCardsViewHolder -> {
                val cards = items[position] as DataItem.PopularCards
                holder.bind(cards.popularCards)
            }
            is PopularUsersViewHolder -> {
                val users = items[position] as DataItem.PopularUsers
                holder.bind(users.popularUsers)
            }
        }
    }

}

sealed class DataItem {

    data class PopularCards(val popularCards: List<Card>) : DataItem() {
        override val id = popularCards.hashCode().toLong()
    }

    data class PopularUsers(val popularUsers: List<User>) : DataItem() {
        override val id = popularUsers.hashCode().toLong()
    }

    abstract val id: Long
}
