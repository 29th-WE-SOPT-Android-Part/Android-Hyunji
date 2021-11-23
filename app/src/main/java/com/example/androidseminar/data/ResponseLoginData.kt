package com.example.androidseminar.data

data class ResponseLoginData(
    val status: Int,
    val success: String,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int,
        val name: String,
        val email: String
    )
}
