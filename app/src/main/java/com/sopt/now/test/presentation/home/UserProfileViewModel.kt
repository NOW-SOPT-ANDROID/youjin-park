package com.sopt.now.test.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.response.ResponseFriendDto
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import com.sopt.now.test.data.repository.UserProfileRepository
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val userProfileRepository: UserProfileRepository
) : ViewModel() {
    private val _getUserProfileLiveData = MutableLiveData<UiState<ResponseUserInfoDto>>()
    val getUserInfoProfileLiveData: MutableLiveData<UiState<ResponseUserInfoDto>> =
        _getUserProfileLiveData

    private val _getFriendProfileLiveData = MutableLiveData<UiState<ResponseFriendDto>>()
    val getFriendProfileLiveData: MutableLiveData<UiState<ResponseFriendDto>> =
        _getFriendProfileLiveData

    init {
        getUserProfile()
        getFriendProfile()
    }

    // 사용자 프로필 가져오기
    private fun getUserProfile() {
        viewModelScope.launch {
            val result = userProfileRepository.getUserProfile()
            _getUserProfileLiveData.value = result
        }
    }

    // 친구 프로필 가져오기
    private fun getFriendProfile() {
        viewModelScope.launch {
            val result = userProfileRepository.getFriendProfile()
            _getFriendProfileLiveData.value = result
        }
    }
}