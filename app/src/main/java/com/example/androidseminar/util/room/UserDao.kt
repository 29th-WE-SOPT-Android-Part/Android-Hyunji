package com.example.androidseminar.util.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface UserDao {
    @Insert
    fun insert(userLogindata:UserLoginData)

    @Update
    fun update(userLogindata:UserLoginData)

    @Delete
    fun delete(userLogindata:UserLoginData)
}