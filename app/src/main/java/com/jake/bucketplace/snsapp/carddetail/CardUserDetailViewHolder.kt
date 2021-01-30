package com.jake.bucketplace.snsapp.carddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.NavHomeDirections
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.databinding.ItemVerticalUserBinding
import com.jake.bucketplace.snsapp.domain.model.User

class CardUserDetailViewHolder constructor(
    private val  binding: ItemVerticalUserBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: User) {
        binding.apply {
            user = item
            executePendingBindings()
            itemUserNickName.text = binding.root.context.getString(R.string.card_detail_user_nickname, item.nickName)
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
        fun from(parent: ViewGroup): CardUserDetailViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemVerticalUserBinding.inflate(layoutInflater, parent, false)
            return CardUserDetailViewHolder(binding)
        }
    }
}