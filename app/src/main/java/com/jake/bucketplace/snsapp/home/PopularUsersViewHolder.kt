package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.HorizontalUserListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalListWithTitleBinding
import com.jake.bucketplace.snsapp.domain.model.User

class PopularUsersViewHolder constructor(
    private val binding: ItemHorizontalListWithTitleBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<User>) {
        binding.apply {
            title = binding.root.context.getString(R.string.home_popular_users_title)
            itemListView.adapter = HorizontalUserListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PopularUsersViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHorizontalListWithTitleBinding.inflate(layoutInflater, parent, false)
            return PopularUsersViewHolder(binding)
        }
    }
}