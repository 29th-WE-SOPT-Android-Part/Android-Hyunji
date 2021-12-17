package com.example.androidseminar.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLoginData(
    var id:String,
    var password:String,
    var autoSelected:Boolean
){
    @PrimaryKey(autoGenerate = true) var key:Int=0
}
