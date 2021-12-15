package com.example.androidseminar.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidseminar.data.RequestLoginData
import com.example.androidseminar.data.ResponseLoginData
import com.example.androidseminar.databinding.ActivitySignInBinding
import com.example.androidseminar.util.BaseActivity
import com.example.androidseminar.api.ServiceCreator
import com.example.androidseminar.data.ResponseWrapper
import com.example.androidseminar.util.SOPTSharedPreferences
import com.example.androidseminar.util.shortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity<ActivitySignInBinding>({ ActivitySignInBinding.inflate(it)}) {

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var intentHome: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentHome = Intent(this, HomeActivity::class.java)

        getSignUpInfo()
        btnLoginClick()
        isAutoLogin()
        btnRegisterClick()
    }

    private fun getSignUpInfo() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val id = it.data?.getStringExtra("id")
                Log.d("SignInActivity", id.toString())
                binding.homeIdEdit.setText(id)

                val pw = it.data?.getStringExtra("pw")
                Log.d("SignInActivity", pw.toString())
                binding.homePwEdit.setText(pw)

            } else {
                Log.d("SignInActivity", "result failed")
            }
        }
    }

    private fun btnLoginClick(){
        binding.btnLogin.setOnClickListener {
            initNetwork()
        }
        binding.ibAutoLogin.setOnClickListener {
            binding.ibAutoLogin.isSelected=!binding.ibAutoLogin.isSelected
            SOPTSharedPreferences.setAutoLogin(this,binding.ibAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin(){ //TODO 안드세미나 따라한거. 수정 필요할수도
        if(SOPTSharedPreferences.getAutoLogin(this)){
            shortToast("자동로그인 되었습니다")
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

    private fun initNetwork(){
        val requestLoginData= RequestLoginData(
            email=binding.homeIdEdit.text.toString(),
            password=binding.homePwEdit.text.toString()
        )

        val call: Call<ResponseWrapper<ResponseLoginData>> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object: Callback<ResponseWrapper<ResponseLoginData>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseLoginData>>,
                response: Response<ResponseWrapper<ResponseLoginData>>
            ) {
                if(response.isSuccessful){
                    val data=response.body()?.data

                    //Toast.makeText(this@SignInActivity,"${data?.name}님 반갑습니다!",Toast.LENGTH_LONG).show()
                    shortToast("${data?.name}님 반갑습니다!")
                    startActivity(intentHome)
                }else{
                    //Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.",Toast.LENGTH_LONG).show()
                    shortToast("로그인에 실패하셨습니다.")
                }
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseLoginData>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }

    private fun btnRegisterClick() {
        binding.btnRegister.setOnClickListener {
            activityResultLauncher.launch(Intent(this, SignUpActivity::class.java))
        }
    }
}