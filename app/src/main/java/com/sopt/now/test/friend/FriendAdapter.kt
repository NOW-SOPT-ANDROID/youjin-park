package com.sopt.now.test.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding

class FriendAdapter(private val profiles: List<Friend>) : RecyclerView.Adapter<BaseViewHolder>() {

    // 첫 번째 아이템
    private val FIRST_ITEM_POSITION = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            Friend.TYPE_USER -> {
                val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding)
            }
            Friend.TYPE_FRIEND -> {
                val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val profile = profiles[position]

        when (holder) {
            is FriendViewHolder -> {
                holder.onBind(profile)
            }
            is UserViewHolder -> {
                holder.onBind(profile)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            FIRST_ITEM_POSITION -> Friend.TYPE_USER
            else -> Friend.TYPE_FRIEND
        }
    }

    override fun getItemCount(): Int = profiles.size
}