package com.example.androidseminar.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.androidseminar.R
import com.example.androidseminar.data.ResponseUserData
import com.example.androidseminar.data.ResponseUserEmailData
import com.example.androidseminar.data.User
import com.example.androidseminar.databinding.FragmentProfileBinding
import com.example.androidseminar.util.BaseFragment
import com.example.androidseminar.api.ServiceCreator
import com.example.androidseminar.data.ResponseWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.user = User("윤현지", "hyunji24", "나는야 멋쟁이")
        initTransactionEvent()
        setProfileImg()
        binding.ivUser.setOnClickListener {
            initUserNetwork()
        }
        binding.ivEmail.setOnClickListener {
            initUserEmailNetwork()
        }
    }

    private fun initTransactionEvent() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.container_home, FollowerFragment())
            .commit()

        binding.profileFollowerBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container_home, FollowerFragment())
                .commit()
        }

        binding.profileRepositoryBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container_home, RepositoryFragment())
                .commit()
        }
    }



    private fun setProfileImg() {
        Glide.with(this)
            .load("https://yt3.ggpht.com/ytc/AKedOLTBmVN3RYeIJpA6Rlmx1vloR3PGaDYR6sfoCTb4=s900-c-k-c0x00ffffff-no-rj")
            .circleCrop()
            .into(binding.profileIv)
    }

    private fun initUserNetwork(){
        val call: Call<ResponseWrapper<ResponseUserData>> = ServiceCreator.sampleService.getUserData(1)

        call.enqueue(object:Callback<ResponseWrapper<ResponseUserData>>{
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseUserData>>,
                response: Response<ResponseWrapper<ResponseUserData>>
            ) {
                if(response.isSuccessful){
                val data=response.body()?.data
                Toast.makeText(requireContext(),"id ${data?.id}번: ${data?.name}님 조회 성공", Toast.LENGTH_SHORT).show()
            }else {
                    Toast.makeText(requireContext(), "유저 조회 실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseUserData>>, t: Throwable) {
                Toast.makeText(requireContext(), "서버 에러", Toast.LENGTH_LONG).show()
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun initUserEmailNetwork(){
        val call: Call<ResponseWrapper<ResponseUserEmailData>> = ServiceCreator.sampleService.getUserByEmail("kimwy1997@gmail.com")

        call.enqueue(object:Callback<ResponseWrapper<ResponseUserEmailData>>{
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseUserEmailData>>,
                response: Response<ResponseWrapper<ResponseUserEmailData>>
            ) {
                if(response.isSuccessful){
                    val data=response.body()?.data
                    Toast.makeText(requireContext()," ${data?.email}: ${data?.name}님 조회 성공", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(requireContext(), "유저 조회 실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseUserEmailData>>, t: Throwable) {
                Toast.makeText(requireContext(), "서버 에러", Toast.LENGTH_LONG).show()
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}