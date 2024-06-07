package com.sopt.now.test.data.repository

import com.sopt.now.test.core.view.UiState
import com.sopt.now.test.data.ServicePool
import com.sopt.now.test.data.dto.response.ResponseFriendDto
import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
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