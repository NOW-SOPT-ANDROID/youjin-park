package com.sopt.now.test.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _postSignUpLiveData: MutableLiveData<UiState<ResponseAuthDto>> = MutableLiveData()
    val postSignUpLiveData: MutableLiveData<UiState<ResponseAuthDto>> = _postSignUpLiveData

    fun postSignUp(requestSignUp: RequestSignUpDto) {
        viewModelScope.launch {
            runCatching {
                ServicePool.authService.postSignUp(requestSignUp)
            }.fold(
                { _postSignUpLiveData.value = UiState.Success(it) },
                { _postSignUpLiveData.value = UiState.Failure(it.message.toString()) }
            )
        }
    }
}