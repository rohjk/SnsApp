package com.jake.bucketplace.snsapp.carddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.HorizontalCardListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalListWithTitleBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class RecommendCardsViewHolder constructor(
    private val binding: ItemHorizontalListWithTitleBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<Card>) {
        binding.apply {
            title = binding.root.context.getString(R.string.card_detail_recomend_cards_title)
            itemListView.adapter = HorizontalCardListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): RecommendCardsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHorizontalListWithTitleBinding.inflate(layoutInflater, parent, false)
            return RecommendCardsViewHolder(binding)
        }
    }
}