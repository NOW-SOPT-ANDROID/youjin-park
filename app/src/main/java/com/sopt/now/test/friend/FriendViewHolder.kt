package com.sopt.now.test.friend

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sopt.now.R
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Profile

class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Profile) {
        binding.run {
            ivFriendProfile.load(friendData.userImage)
            tvFriendName.text = friendData.userName
            tvFriendPhone.text = friendData.userInfo
        }
    }
}

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: Profile) {
        binding.run {
            ivMyProfile.load(R.drawable.iv_user_profile)
            tvMyName.text = userData.userName
            tvMyPhone.text = userData.userInfo
        }
    }
}