package com.example.androidseminar.api

import com.example.androidseminar.data.*
import retrofit2.Call
import retrofit2.http.*

interface SampleService {
    @POST("user/login")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseWrapper<ResponseLoginData>>

    @POST("user/signup")
    fun postSignUp(
        @Body body: RequestSignUpData
    ) : Call<ResponseWrapper<ResponseSignUpData>>

    @GET("user/{id}")
    fun getUserData(
        @Path("id")id:Int
    ):Call<ResponseWrapper<ResponseUserData>>


    @GET("user?")
    fun getUserByEmail(
        @Query("email")email:String
    ):Call<ResponseWrapper<ResponseUserEmailData>>


}