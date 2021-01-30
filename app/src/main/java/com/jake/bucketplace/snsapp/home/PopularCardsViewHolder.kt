package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.HorizontalCardListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalListWithTitleBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class PopularCardsViewHolder constructor(
    private val binding: ItemHorizontalListWithTitleBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<Card>) {
        binding.apply {
            itemListTitle.text = binding.root.context.getString(R.string.home_popular_cards_title)
            itemListView.adapter = HorizontalCardListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PopularCardsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHorizontalListWithTitleBinding.inflate(layoutInflater, parent, false)
            return PopularCardsViewHolder(binding)
        }
    }
}