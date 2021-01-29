package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemListCardBinding
import com.jake.bucketplace.snsapp.domain.model.Card

class PopularCardListAdapter constructor(
    private var cards: List<Card>
) : RecyclerView.Adapter<PopularCardListAdapter.CardViewHolder>() {

    class CardViewHolder constructor(private val binding: ItemListCardBinding) :
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
            ItemListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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