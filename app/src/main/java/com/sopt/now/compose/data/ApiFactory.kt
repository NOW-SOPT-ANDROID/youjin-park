package com.sopt.now.compose.data

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
    lateinit var userPreference: UserPreference

    fun initializeUserPreference(userPreference: UserPreference) {
        ApiFactory.userPreference = userPreference
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .build()

    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!ApiFactory::userPreference.isInitialized) {
                throw IllegalStateException("UserPreference is not initialized")
            }

            val newRequest = chain.request().newBuilder()
                .addHeader("memberId", userPreference.getUserId().toString())
                .build()
            Log.d("userPreference API", "${userPreference.getUserId().toString()}")
            return chain.proceed(newRequest)
        }
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val userService = ApiFactory.create<UserService>()
}