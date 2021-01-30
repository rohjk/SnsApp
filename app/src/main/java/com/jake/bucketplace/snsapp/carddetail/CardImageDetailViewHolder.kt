package com.jake.bucketplace.snsapp.carddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemCardImageDetailBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class CardImageDetailViewHolder constructor(
    private val  binding: ItemCardImageDetailBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Card) {
        binding.apply {
            card = item
        }
    }

    companion object {
        fun from(parent: ViewGroup): CardImageDetailViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCardImageDetailBinding.inflate(layoutInflater, parent, false)
            return CardImageDetailViewHolder(binding)
        }
    }
}