package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.CardHorizontalListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemListWithTitleBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class PopularCardsViewHolder constructor(
    private val binding: ItemListWithTitleBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<Card>) {
        binding.apply {
            itemListTitle.text = binding.root.context.getString(R.string.home_popular_cards_title)
            itemListView.adapter = CardHorizontalListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PopularCardsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListWithTitleBinding.inflate(layoutInflater, parent, false)
            return PopularCardsViewHolder(binding)
        }
    }
}