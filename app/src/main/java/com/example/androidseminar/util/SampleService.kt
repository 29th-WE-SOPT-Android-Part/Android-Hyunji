package com.example.androidseminar.util

import com.example.androidseminar.data.RequestLoginData
import com.example.androidseminar.data.RequestSignUpData
import com.example.androidseminar.data.ResponseLoginData
import com.example.androidseminar.data.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SampleService {
    @POST("user/login")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseSignUpData>


}