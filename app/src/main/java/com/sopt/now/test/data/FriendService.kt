package com.sopt.now.test.data

import com.sopt.now.test.data.dto.response.ResponseFriendDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    // 친구 프로필
    @GET("users")
    suspend fun getFriendInfo(
        @Query("page") page: Int,
    ): ResponseFriendDto
}