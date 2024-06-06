package com.sopt.now.test.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.response.ResponseFriendDto
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
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

    // 사용자 프로필
    private fun getUserProfile() {
        viewModelScope.launch {
            runCatching {
                ServicePool.userService.getUserInfo()
            }.fold(
                { _getUserProfileLiveData.value = UiState.Success(it) },
                { _getUserProfileLiveData.value = UiState.Failure(it.message.toString()) }
            )
        }
    }

    // 친구 프로필
    private fun getFriendProfile() {
        viewModelScope.launch {
            runCatching {
                ServicePool.friendService.getFriendInfo(1)
            }.fold(
                { _getFriendProfileLiveData.value = UiState.Success(it) },
                { _getFriendProfileLiveData.value = UiState.Failure(it.message.toString()) }
            )
        }
    }
}