package com.jake.bucketplace.snsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
            binding.setClickListener { view ->
                Toast.makeText(view.context, "${binding.card?.id}", Toast.LENGTH_SHORT).show()
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