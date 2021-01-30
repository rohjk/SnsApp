package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.UserListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemListPopularBinding
import com.jake.bucketplace.snsapp.domain.model.User

class PopularUsersViewHolder constructor(
    private val binding: ItemListPopularBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<User>) {
        binding.apply {
            itemPopularTitle.text = binding.root.context.getString(R.string.home_popular_users_title)
            homePopularListView.adapter = UserListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PopularUsersViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListPopularBinding.inflate(layoutInflater, parent, false)
            return PopularUsersViewHolder(binding)
        }
    }
}