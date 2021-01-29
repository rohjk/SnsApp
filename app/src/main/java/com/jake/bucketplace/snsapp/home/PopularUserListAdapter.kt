package com.jake.bucketplace.snsapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemListUserBinding
import com.jake.bucketplace.snsapp.domain.model.User

class PopularUserListAdapter constructor(
    private var users: List<User>
) : RecyclerView.Adapter<PopularUserListAdapter.CardViewHolder>() {

    class CardViewHolder constructor(private val binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }
        init {
            binding.setClickListener { view ->
                Toast.makeText(view.context, "${binding.user?.nickName}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
       return users.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       val user = users[position]
        holder.bind(user)
    }

    fun update(items: List<User>) {
        this.users = items
        notifyDataSetChanged()
    }

}