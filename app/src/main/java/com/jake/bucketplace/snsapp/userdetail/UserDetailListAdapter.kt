package com.jake.bucketplace.snsapp.userdetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.domain.model.User
import java.lang.ClassCastException

private const val ITEM_VIEW_TYPE_USER= 0

class UserDetailListAdapter constructor(
    private var items: List<DataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun sumbitUser(user: User) {
        val itemList: MutableList<DataItem> = mutableListOf()
        itemList.add(DataItem.UserItem(user))
        this.items = itemList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.UserItem -> ITEM_VIEW_TYPE_USER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_USER -> UserDetailUserViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserDetailUserViewHolder -> {
                val user = items[position] as DataItem.UserItem
                holder.bind(user.user)
            }
        }
    }

}

sealed class DataItem {

    data class UserItem(val user: User) : DataItem() {
        override val id = user.hashCode().toLong()
    }

    abstract val id: Long
}
