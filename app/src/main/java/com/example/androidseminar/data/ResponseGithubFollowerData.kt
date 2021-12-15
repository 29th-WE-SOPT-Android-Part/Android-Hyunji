package com.example.androidseminar.data

import com.google.gson.annotations.SerializedName

data class ResponseGithubFollowerData(
    @SerializedName("login")
    val id:String,
    @SerializedName("avatar_url")
    val profileImg:String
)