package com.example.androidseminar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment(), ItemDragListener {

    private lateinit var adapter: FollowerRecyclerViewAdapter
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var itemTouchHelper: ItemTouchHelper

    val followerData = mutableListOf(
        Info(R.drawable.github_icon, "윤현지", "안드YB"),
        Info(R.drawable.github_icon, "홍길동", "안드OB"),
        Info(R.drawable.github_icon, "김길동", "서버YB"),
        Info(R.drawable.github_icon, "박길동", "아요YB")

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        initFollowerRecyclerView()

        return binding.root
    }


    private fun initFollowerRecyclerView() {
        adapter = FollowerRecyclerViewAdapter(this)
        adapter.infoList = followerData

        binding.followerRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecyclerview)
        binding.followerRecyclerview.adapter = adapter


        //recyclerview 아이템 간격 조절
        binding.followerRecyclerview.addItemDecoration(
            HorizontalItemDecoration(
                10f,
                10f,
                20,
                ContextCompat.getColor(requireContext(), R.color.hot_pink)
            )
        )

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

}