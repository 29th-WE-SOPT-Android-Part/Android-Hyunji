package com.example.androidseminar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidseminar.R
import com.example.androidseminar.activity.SignInActivity
import com.example.androidseminar.databinding.FragmentThirdOnboardingBinding
import com.example.androidseminar.util.BaseFragment


class ThirdOnboardingFragment : BaseFragment<FragmentThirdOnboardingBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentThirdOnboardingBinding {
        return FragmentThirdOnboardingBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val intent= Intent(activity,SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        //findNavController().navigate(R.id.pop_back_to_first)
    }



}