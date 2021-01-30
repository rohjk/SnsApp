package com.jake.bucketplace.snsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalListUserBinding
import com.jake.bucketplace.snsapp.domain.model.User

class UserHorizontalListAdapter constructor(
    private var users: List<User>
) : RecyclerView.Adapter<UserHorizontalListAdapter.CardViewHolder>() {

    class CardViewHolder constructor(private val binding: ItemHorizontalListUserBinding) :
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
            ItemHorizontalListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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