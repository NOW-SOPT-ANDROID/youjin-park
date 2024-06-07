package com.sopt.now.test.presentation.home

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.test.data.Profile

class FriendProfileViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Profile) {
        binding.run {
            ivFriendProfile.load(friendData.userImage)
            tvFriendName.text = friendData.userName
            tvFriendPhone.text = friendData.userInfo
        }
    }
}