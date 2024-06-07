package com.sopt.now.compose.data.repository

import com.sopt.now.compose.core.view.UiState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.response.ResponseFriendDto
import com.sopt.now.compose.data.dto.response.ResponseUserInfoDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserProfileRepository {
    suspend fun getUserProfile(): UiState<ResponseUserInfoDto> {
        return withContext(Dispatchers.IO) {
            runCatching {
                ServicePool.userService.getUserInfo()
            }.fold(
                { UiState.Success(it) },
                { UiState.Failure(it.message.toString()) }
            )
        }
    }

    suspend fun getFriendProfile(): UiState<ResponseFriendDto> {
        return withContext(Dispatchers.IO) {
            runCatching {
                ServicePool.friendService.getFriendInfo(1)
            }.fold(
                { UiState.Success(it) },
                { UiState.Failure(it.message.toString()) }
            )
        }
    }
}