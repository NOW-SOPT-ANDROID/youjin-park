package com.sopt.now.test.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import com.sopt.now.test.data.repository.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {
    private val _postSignUpLiveData: MutableLiveData<UiState<ResponseAuthDto>> = MutableLiveData()
    val postSignUpLiveData: MutableLiveData<UiState<ResponseAuthDto>> = _postSignUpLiveData

    fun postSignUp(requestSignUp: RequestSignUpDto) {
        viewModelScope.launch {
            val result = signUpRepository.postSignUp(requestSignUp)
            _postSignUpLiveData.value = result
        }
    }
}