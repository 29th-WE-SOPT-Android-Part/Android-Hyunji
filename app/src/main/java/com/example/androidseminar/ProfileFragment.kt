package com.example.androidseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidseminar.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.user=User("윤현지","hyunji24","나는야 멋쟁이")

        initTransactionEvent()
        setProfileImg()

        return binding.root
    }

        private fun initTransactionEvent(){
        parentFragmentManager
            .beginTransaction()
            .add(R.id.container_home,FollowerFragment())
            .commit()



        binding.profileFollowerBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container_home,FollowerFragment())
                .commit()
        }

        binding.profileRepositoryBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container_home,RepositoryFragment())
                .commit()
        }

    }

        private fun setProfileImg(){
            Glide.with(this)
                .load("https://yt3.ggpht.com/ytc/AKedOLTBmVN3RYeIJpA6Rlmx1vloR3PGaDYR6sfoCTb4=s900-c-k-c0x00ffffff-no-rj")
                .circleCrop()
                .into(binding.profileIv)

        }

}