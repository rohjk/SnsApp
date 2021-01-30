package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.R
import com.jake.bucketplace.snsapp.adapters.CardHorizontalListAdapter
import com.jake.bucketplace.snsapp.databinding.ItemListPopularBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class PopularCardsViewHolder constructor(
    private val binding: ItemListPopularBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: List<Card>) {
        binding.apply {
            itemPopularTitle.text = binding.root.context.getString(R.string.home_popular_cards_title)
            homePopularListView.adapter = CardHorizontalListAdapter(item)
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PopularCardsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListPopularBinding.inflate(layoutInflater, parent, false)
            return PopularCardsViewHolder(binding)
        }
    }
}