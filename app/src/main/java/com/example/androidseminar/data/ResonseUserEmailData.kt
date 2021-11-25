package com.example.androidseminar.data

data class ResponseUserEmailData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: UserEmailData
){
    data class UserEmailData(
        val id: Int,
        val name: String,
        val email:String
    )
}

