package com.example.androidseminar.util

import android.content.Context
import android.widget.Toast

//확장함수 클래스는 static형태여서 클래스나 이런게 따로 필요 없음!

fun Context.shortToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}