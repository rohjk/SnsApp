package com.jake.bucketplace.snsapp.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemVerticalUserBinding
import com.jake.bucketplace.snsapp.domain.model.User

class UserDetailUserViewHolder constructor(
    private val  binding: ItemVerticalUserBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: User) {
        binding.apply {
            user = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): UserDetailUserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemVerticalUserBinding.inflate(layoutInflater, parent, false)
            return UserDetailUserViewHolder(binding)
        }
    }
}