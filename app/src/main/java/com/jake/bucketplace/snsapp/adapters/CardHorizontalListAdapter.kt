package com.jake.bucketplace.snsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.NavHomeDirections
import com.jake.bucketplace.snsapp.carddetail.CardDetailFragmentDirections
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalListCardBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class CardHorizontalListAdapter constructor(
    private var cards: List<Card>
) : RecyclerView.Adapter<CardHorizontalListAdapter.CardViewHolder>() {

    class CardViewHolder constructor(private val binding: ItemHorizontalListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Card) {
            binding.apply {
                card = item
                executePendingBindings()
            }
        }
        init {
            binding.apply {
                setClickListener { view ->
                    card?.apply {
                        val direction: NavDirections = NavHomeDirections.actionGlobalCardDetailFragment(id)
                        view.findNavController().navigate(direction)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            ItemHorizontalListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
       return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       val card = cards[position]
        holder.bind(card)
    }

    fun update(items: List<Card>) {
        this.cards = items
        notifyDataSetChanged()
    }

}