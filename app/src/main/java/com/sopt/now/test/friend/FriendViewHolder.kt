package com.sopt.now.test.friend

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.api.load
import com.sopt.now.R
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Profile

sealed class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

class FriendViewHolder(private val binding: ItemFriendBinding) : BaseViewHolder(binding) {
    fun onBind(friendData: Profile) {
        binding.run {
            ivFriendProfile.load(friendData.userImage)
            tvFriendName.text = friendData.userName
            tvFriendPhone.text = friendData.userInfo
        }
    }
}

class UserViewHolder(private val binding: ItemUserBinding) : BaseViewHolder(binding) {
    fun onBind(userData: Profile) {
        binding.run {
            ivMyProfile.load(R.drawable.iv_user_profile)
            tvMyName.text = userData.userName
            tvMyPhone.text = userData.userInfo
        }
    }
}