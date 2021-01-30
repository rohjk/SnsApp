package com.jake.bucketplace.snsapp.carddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemCardImageWithDescriptionBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class CardDetailImageDetailViewHolder constructor(
    private val  binding: ItemCardImageWithDescriptionBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Card) {
        binding.apply {
            card = item
        }
    }

    companion object {
        fun from(parent: ViewGroup): CardDetailImageDetailViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCardImageWithDescriptionBinding.inflate(layoutInflater, parent, false)
            return CardDetailImageDetailViewHolder(binding)
        }
    }
}