package com.example.androidseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidseminar.databinding.ActivitySignInBinding
import com.example.androidseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpBinding.inflate(layoutInflater)

        binding.btnRegisterFinish.setOnClickListener {
            if(canRegister()) { //칸 다 채웠을 때
                    Log.d("SignInActivity", binding.registerIdEdit.text.toString())
                    Log.d("SignInActivity", binding.registerIdEdit.text.toString())
                val intent_s= Intent(this,SignInActivity::class.java).apply {
                    putExtra("id",binding.registerIdEdit.text.toString())
                    putExtra("pw",binding.registerPwEdit.text.toString())
                }


                setResult(RESULT_OK,intent_s)
                finish() } //다시 SignInActivity로 이동
            else{
                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }



        setContentView(binding.root)
    }

    fun canRegister():Boolean{
        return(binding.registerIdEdit.text.toString().isNotEmpty() && binding.registerPwEdit.text.toString().isNotEmpty() && binding.registerNameEdit.text.toString().isNotEmpty())
    }
}