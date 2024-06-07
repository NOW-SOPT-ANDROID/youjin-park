package com.sopt.now.test.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import com.sopt.now.test.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
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