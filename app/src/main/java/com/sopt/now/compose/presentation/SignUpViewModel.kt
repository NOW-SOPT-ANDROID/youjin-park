package com.sopt.now.compose.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.core.view.UiState
import com.sopt.now.compose.data.UserPreference
import com.sopt.now.compose.data.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import com.sopt.now.compose.data.repository.SignUpRepository
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
    private val userPreference: UserPreference
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