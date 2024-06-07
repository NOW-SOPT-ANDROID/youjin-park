package com.sopt.now.compose.data.repository

import com.sopt.now.compose.core.view.UiState
import com.sopt.now.compose.data.ApiFactory
import com.sopt.now.compose.data.ServicePool.authService
import com.sopt.now.compose.data.dto.request.RequestLoginDto
import com.sopt.now.compose.data.dto.response.ResponseAuthDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository {
    suspend fun postLogin(request: RequestLoginDto): UiState<ResponseAuthDto> {
        return withContext(Dispatchers.IO) {
            runCatching {
                authService.postLogin(request)
            }.fold(
                { response ->
                    val userId = response.headers()["location"]
                    if (userId != null) {
                        ApiFactory.userPreference.saveUserId(userId)
                    }
                    UiState.Success(
                        response.body() ?: ResponseAuthDto(
                            -1,
                            "Empty response body"
                        )
                    )
                },
                { UiState.Failure(it.message.toString()) }
            )
        }
    }
}