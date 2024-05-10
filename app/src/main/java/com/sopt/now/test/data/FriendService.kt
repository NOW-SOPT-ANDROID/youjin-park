package com.sopt.now.test.data

import com.sopt.now.test.data.dto.response.ResponseFriendDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("users")
    fun getFriendInfo(
        @Query("page") page: Int,
    ): Call<ResponseFriendDto>
}