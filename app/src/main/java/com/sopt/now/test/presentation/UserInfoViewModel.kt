package com.sopt.now.test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import kotlinx.coroutines.launch

class UserInfoViewModel : ViewModel() {
    private val _getUserInfoLiveData: MutableLiveData<UiState<ResponseUserInfoDto>> = MutableLiveData()
    val getUserInfoLiveData: MutableLiveData<UiState<ResponseUserInfoDto>> = _getUserInfoLiveData

    init{
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            runCatching {
                ServicePool.userService.getUserInfo()
            }.fold(
                { _getUserInfoLiveData.value = UiState.Success(it) },
                { _getUserInfoLiveData.value = UiState.Failure(it.message.toString()) }
            )
        }
    }
}