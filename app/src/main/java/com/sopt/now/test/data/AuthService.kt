package com.sopt.now.test.data

import com.sopt.now.test.data.dto.request.RequestLoginDto
import com.sopt.now.test.data.dto.request.RequestSignUpDto
import com.sopt.now.test.data.dto.response.ResponseAuthDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 회원가입
    @POST("member/join")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto
    ): ResponseAuthDto

    // 로그인
    @POST("member/login")
    suspend fun postLogin(
        @Body request: RequestLoginDto,
    ): Response<ResponseAuthDto>
}