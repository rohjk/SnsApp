package com.jake.bucketplace.snsapp.carddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    companion object {
        fun from(parent: ViewGroup): CardUserDetailViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemVerticalUserBinding.inflate(layoutInflater, parent, false)
            return CardUserDetailViewHolder(binding)
        }
    }
}