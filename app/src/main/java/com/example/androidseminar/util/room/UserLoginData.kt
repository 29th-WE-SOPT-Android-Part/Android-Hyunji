package com.example.androidseminar.util.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLoginData(
    @PrimaryKey(autoGenerate = true) val key:Int=0,
    val id:String,
    val password:String,
    val autoSelected:Boolean
)