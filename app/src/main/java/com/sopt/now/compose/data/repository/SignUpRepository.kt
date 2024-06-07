package com.sopt.now.compose.data.repository

import com.sopt.now.compose.core.view.UiState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpRepository {
    suspend fun postSignUp(requestSignUp: RequestSignUpDto): UiState<ResponseAuthDto> {
        return withContext(Dispatchers.IO) {
            runCatching {
                ServicePool.authService.postSignUp(requestSignUp)
            }.fold(
                { UiState.Success(it) },
                { UiState.Failure(it.message.toString()) }
            )
        }
    }
}