package com.example.androidseminar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidseminar.R
import com.example.androidseminar.data.User
import com.example.androidseminar.databinding.FragmentProfileBinding
import com.example.androidseminar.util.BaseFragment

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
}