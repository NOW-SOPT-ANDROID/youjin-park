package com.sopt.now.test.presentation.home

import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sopt.now.R
import com.sopt.now.databinding.ItemUserBinding
import com.sopt.now.test.data.Profile

class UserProfileViewHolder(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: Profile) {
        binding.run {
            ivMyProfile.load(R.drawable.iv_user_profile)
            tvMyName.text = userData.userName
            tvMyPhone.text = userData.userInfo
        }
    }
}