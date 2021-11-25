package com.example.androidseminar.util

import com.example.androidseminar.data.ResponseGithubFollowerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/followers")
    fun getGithubFollowers(
        @Path("username") username:String
    ) : Call<List<ResponseGithubFollowerData>>

}