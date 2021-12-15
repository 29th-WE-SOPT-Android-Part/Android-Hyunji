package com.example.androidseminar.api

import com.example.androidseminar.data.ResponseGithubBioData
import com.example.androidseminar.data.ResponseGithubFollowerData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/followers")
    fun getGithubFollowers(
        @Path("username") username:String
    ) : Call<List<ResponseGithubFollowerData>>

    @GET("users/{username}")
    fun getGithubBio(
        @Path("username") username:String
    ) : Call<ResponseGithubBioData>

}