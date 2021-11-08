package com.example.androidseminar

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService {
    //@Headers("Content-Type:application/json")
    @POST("user/login")
    fun postLogin(
        @Body body:RequestLoginData
    ) : Call<ResponseLoginData>

    //@Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body:RequestSignUpData
    ) : Call<ResponseSignUpData>


}