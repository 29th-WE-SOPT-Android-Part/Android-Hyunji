package com.example.androidseminar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidseminar.R
import com.example.androidseminar.databinding.FragmentFirstOnboardingBinding
import com.example.androidseminar.util.BaseFragment


class FirstOnboardingFragment : BaseFragment<FragmentFirstOnboardingBinding>() {


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFirstOnboardingBinding {
        return FragmentFirstOnboardingBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstOnboardingFragment_to_secondOnboardingFragment)
        }
    }



}