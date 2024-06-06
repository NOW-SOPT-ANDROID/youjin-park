package com.sopt.now.test.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ApiFactory
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _postLoginLiveData: MutableLiveData<UiState<ResponseAuthDto>> = MutableLiveData()
    val postLoginLiveData: MutableLiveData<UiState<ResponseAuthDto>> = _postLoginLiveData

    fun postLogin(request: RequestLoginDto) {
        viewModelScope.launch {
            runCatching {
                ServicePool.authService.postLogin(request)
            }.fold(
                {
                    val response = ServicePool.authService.postLogin(request)
                    val userId = response.headers()["location"]
                    if (userId != null) {
                        ApiFactory.userPreference.saveUserId(userId)
                    }
                    _postLoginLiveData.value = UiState.Success(
                        response.body() ?: ResponseAuthDto(
                            -1,
                            "Empty response body"
                        )
                    )
                },
                { _postLoginLiveData.value = UiState.Failure(it.message.toString()) }
            )
        }
    }
}