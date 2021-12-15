package com.example.androidseminar.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubCreator {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun provideOkHttpClient(
        interceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor).build()
        }

    class AppInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("accept", "application/vnd.github.v3+json")
                .build()

            proceed(newRequest)
        }
    }

    val githubService: GithubService = retrofit.create(GithubService::class.java)
}