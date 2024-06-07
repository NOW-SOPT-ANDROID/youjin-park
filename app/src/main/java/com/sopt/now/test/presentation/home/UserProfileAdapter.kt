package com.sopt.now.test.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Profile

class UserProfileAdapter(private val profiles: List<Profile>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_USER) {
            val binding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UserProfileViewHolder(binding)
        } else {
            val binding =
                ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FriendProfileViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profile = profiles[position]

        when (holder) {
            is UserProfileViewHolder -> {
                holder.onBind(profile)
            }

            is FriendProfileViewHolder -> {
                holder.onBind(profile)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == FIRST_ITEM_POSITION) TYPE_USER else TYPE_FRIEND
    }

    override fun getItemCount(): Int = profiles.size

    companion object {
        const val TYPE_USER = 0
        const val TYPE_FRIEND = 1
        const val FIRST_ITEM_POSITION = 0
    }
}