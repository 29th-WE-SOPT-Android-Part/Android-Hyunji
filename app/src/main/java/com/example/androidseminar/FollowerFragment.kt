package com.example.androidseminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidseminar.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {

    private lateinit var adapter:FollowerRecyclerViewAdapter
    private lateinit var binding: FragmentFollowerBinding


    val followerData=mutableListOf<Info>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentFollowerBinding.inflate(inflater,container,false)



        addData()
        initFollowerRecyclerView()



        return binding.root
    }

    fun addData(){
        followerData.add(Info(R.drawable.github_icon,"윤현지","안드YB"))
        followerData.add(Info(R.drawable.github_icon,"윤현지","안드YB"))
        followerData.add(Info(R.drawable.github_icon,"윤현지","안드YB"))
        followerData.add(Info(R.drawable.github_icon,"윤현지","안드YB"))
    }

    fun initFollowerRecyclerView(){
        adapter= FollowerRecyclerViewAdapter()
        adapter.infoList=followerData
        binding.followerRecyclerview.adapter=adapter
        binding.followerRecyclerview.layoutManager=LinearLayoutManager(requireContext())

    }

}