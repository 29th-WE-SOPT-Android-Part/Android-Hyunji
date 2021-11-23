package com.example.androidseminar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidseminar.databinding.FragmentFollowerBinding
import com.example.androidseminar.databinding.FragmentHomeBinding
import com.example.androidseminar.databinding.FragmentHomeFollowerBinding
import com.example.androidseminar.util.BaseFragment


class HomeFollowerFragment : BaseFragment<FragmentHomeFollowerBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeFollowerBinding {
        return FragmentHomeFollowerBinding.inflate(inflater,container,false)
    }

}