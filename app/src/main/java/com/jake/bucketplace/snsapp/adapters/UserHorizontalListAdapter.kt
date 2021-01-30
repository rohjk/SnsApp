package com.jake.bucketplace.snsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jake.bucketplace.snsapp.databinding.ItemHorizontalUserBinding
import com.jake.bucketplace.snsapp.domain.model.User

class UserHorizontalListAdapter constructor(
    private var users: List<User>
) : RecyclerView.Adapter<UserHorizontalListAdapter.UserViewHolder>() {

    class UserViewHolder constructor(private val binding: ItemHorizontalUserBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemHorizontalUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
       return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       val user = users[position]
        holder.bind(user)
    }

    fun update(items: List<User>) {
        this.users = items
        notifyDataSetChanged()
    }

}