package com.example.androidseminar.util

import android.content.Context

object SOPTSharedPreferences {
    private const val USER_AUTH = "USER_AUTH"
    private const val AUTO_LOGIN="AUTO_CLEAR"


    fun getAutoLogin(context:Context):Boolean{ //전역으로 부르기위해 context를 매개변수로 받아와야함
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        return preferences.getBoolean(AUTO_LOGIN,false)
    }

    fun setAutoLogin(context: Context,isAuto:Boolean){
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean(AUTO_LOGIN,isAuto)
            .apply()
    }

    fun removeAutoLogin(context: Context){
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .apply()
    }

    fun clearStorage(context:Context){
        val preferecnes=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferecnes.edit()
            .clear()
            .apply()
    }

}