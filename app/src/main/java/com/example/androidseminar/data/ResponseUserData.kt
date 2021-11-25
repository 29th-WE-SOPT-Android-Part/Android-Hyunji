package com.example.androidseminar.data

data class ResponseUserData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserData
){
    data class UserData(
        val id: Int,
        val name: String,
        val email:String
    )
}
