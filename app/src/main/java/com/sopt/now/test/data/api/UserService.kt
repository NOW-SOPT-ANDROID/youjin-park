package com.sopt.now.test.data.api

import com.sopt.now.test.data.dto.response.ResponseUserInfoDto
import retrofit2.http.GET

interface UserService {
    @GET("member/info")
    suspend fun getUserInfo(
    ): ResponseUserInfoDto
}