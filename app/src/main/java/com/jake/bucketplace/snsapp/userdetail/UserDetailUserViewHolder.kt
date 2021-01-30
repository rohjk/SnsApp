package com.jake.bucketplace.snsapp.userdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.NavHomeDirections
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

    init {
        binding.apply {
            setClickListener { view ->
                user?.apply {
                    val direction = NavHomeDirections.actionGlobalUserDetailFragment(id)
                    view.findNavController().navigate(direction)
                }
            }
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