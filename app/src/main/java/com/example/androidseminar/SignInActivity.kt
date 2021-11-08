package com.example.androidseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidseminar.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var intent1: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        intent1 = Intent(this, HomeActivity::class.java)

        getSignUpInfo()
        btnLoginClick()
        btnRegisterClick()



        setContentView(binding.root)
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
    }

    private fun initNetwork(){
        val requestLoginData=RequestLoginData(
            email=binding.homeIdEdit.text.toString(),
            password=binding.homePwEdit.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object: Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                if(response.isSuccessful){
                    val data=response.body()?.data

                    Toast.makeText(this@SignInActivity,"${data?.name}님 반갑습니다!",Toast.LENGTH_LONG).show()
                    startActivity(intent1) //TODO intent1안되면 ppt대로
                }else{
                    Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })
    }

    private fun btnRegisterClick() {
//        binding.btnLogin.setOnClickListener {
//            if (canLogin()) {
//                Toast.makeText(this, "안녕하세요 현지님", Toast.LENGTH_SHORT).show()
//                startActivity(intent1)
//            } else {
//                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
//            }
//        }

        binding.btnRegister.setOnClickListener {
            activityResultLauncher.launch(Intent(this, SignUpActivity::class.java))

        }
    }



    private fun canLogin(): Boolean { //TODO private붙여줌
        return (binding.homeIdEdit.text.toString()
            .isNotEmpty() && binding.homePwEdit.text.toString().isNotEmpty())

    }


}