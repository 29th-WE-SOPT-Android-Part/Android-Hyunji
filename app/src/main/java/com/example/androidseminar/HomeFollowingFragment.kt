package com.example.androidseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidseminar.databinding.FragmentHomeFollowerBinding
import com.example.androidseminar.databinding.FragmentHomeFollowingBinding


class HomeFollowingFragment : Fragment() {

    private lateinit var binding: FragmentHomeFollowingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeFollowingBinding.inflate(inflater, container, false)


        return binding.root


    }

}