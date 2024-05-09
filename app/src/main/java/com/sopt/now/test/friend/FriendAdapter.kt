package com.sopt.now.test.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Profile

class FriendAdapter(private val profiles: List<Profile>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_USER -> {
                val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding)
            }
            TYPE_FRIEND -> {
                val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val profile = profiles[position]

        when (holder) {
            is UserViewHolder -> {
                holder.onBind(profile)
            }
            is FriendViewHolder -> {
                holder.onBind(profile)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            FIRST_ITEM_POSITION -> TYPE_USER
            else -> TYPE_FRIEND
        }
    }
    override fun getItemCount(): Int = profiles.size

    companion object {
        const val TYPE_USER = 0
        const val TYPE_FRIEND = 1
        const val FIRST_ITEM_POSITION = 0
    }
}