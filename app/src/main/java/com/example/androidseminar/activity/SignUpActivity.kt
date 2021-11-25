package com.example.androidseminar.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidseminar.data.RequestSignUpData
import com.example.androidseminar.data.ResponseSignUpData
import com.example.androidseminar.databinding.ActivitySignUpBinding
import com.example.androidseminar.util.BaseActivity
import com.example.androidseminar.api.ServiceCreator
import com.example.androidseminar.data.ResponseWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnRegisterFinish.setOnClickListener {
            initSignUpNetwork()
        }
    }

    private fun initSignUpNetwork() {
        val requestSignUpData = RequestSignUpData(
            email = binding.registerIdEdit.text.toString(),
            name = binding.registerNameEdit.text.toString(),
            password = binding.registerPwEdit.text.toString()
        )

        val call: Call<ResponseWrapper<ResponseSignUpData>> = ServiceCreator.sampleService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignUpData>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseSignUpData>>,
                response: Response<ResponseWrapper<ResponseSignUpData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    val intent2 = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                        putExtra("id", binding.registerIdEdit.text.toString())
                        putExtra("pw", binding.registerPwEdit.text.toString())
                    }
                    Toast.makeText(this@SignUpActivity, "${data?.name}님 회원가입 완료", Toast.LENGTH_LONG)
                        .show()
                    setResult(RESULT_OK, intent2)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseSignUpData>>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "서버 에러", Toast.LENGTH_LONG).show()
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}