package com.example.androidseminar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidseminar.databinding.FragmentFollowerBinding
import com.example.androidseminar.databinding.FragmentHomeFollowerBinding


class HomeFollowerFragment : Fragment() {

    private lateinit var binding: FragmentHomeFollowerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFollowerBinding.inflate(inflater, container, false)


        return binding.root
    }

}