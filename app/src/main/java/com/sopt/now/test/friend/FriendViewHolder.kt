package com.sopt.now.test.friend

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Friend

sealed class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class FriendViewHolder(private val binding: ItemFriendBinding) : BaseViewHolder(binding) {
    fun onBind(friendData: Friend) {
        binding.run {
            ivFriendProfile.setImageResource(friendData.profileImage)
            tvFriendName.text = friendData.name
            tvFriendDescription.text = friendData.selfDescription
        }
    }
}

class UserViewHolder(private val binding: ItemUserBinding) : BaseViewHolder(binding) {
    fun onBind(userData: Friend) {
        binding.run {
            ivMyProfile.setImageResource(userData.profileImage)
            tvMyName.text = userData.name
            tvMyDescription.text = userData.selfDescription
        }
    }
}