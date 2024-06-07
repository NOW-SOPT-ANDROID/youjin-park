package com.sopt.now.compose.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.core.view.UiState
import com.sopt.now.compose.data.UserPreference
import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import com.sopt.now.compose.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val userPreference: UserPreference
) : ViewModel() {
    private val _postLoginLiveData: MutableLiveData<UiState<ResponseAuthDto>> = MutableLiveData()
    val postLoginLiveData: LiveData<UiState<ResponseAuthDto>> = _postLoginLiveData

    fun postLogin(request: RequestLoginDto) {
        viewModelScope.launch {
            val result = loginRepository.postLogin(request)
            _postLoginLiveData.value = result
        }
    }
}