package com.example.androidseminar

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
        Info("https://yt3.ggpht.com/ytc/AKedOLTBmVN3RYeIJpA6Rlmx1vloR3PGaDYR6sfoCTb4=s900-c-k-c0x00ffffff-no-rj", "윤현지", "안드YB"),
        Info("https://mblogthumb-phinf.pstatic.net/MjAyMTAxMzFfMjIg/MDAxNjEyMDM1NDcyOTE4.3cRndWUdyeLevLJ3AmljQXVcIHbD_mX0hbra4PVkxgsg.0zn8FDRU_c28VWf8qhCO7Zy1v6QN6dnttfSGSLvrwssg.JPEG.letyourselfglow/IMG_1699.JPG?type=w800", "홍길동", "안드OB"),
        Info("https://mblogthumb-phinf.pstatic.net/MjAyMDExMzBfMjY1/MDAxNjA2NzAxNzIzMDkz.mXpHE4L6KPe-c9o5QYP6pml_ck7Up-OsPeMR-cRemCUg.YKL7EaPVhrpTnTB2giNhEn6pVF6SvYb-LLRvp8ulNRcg.JPEG.marxela/IMG_0511.JPG?type=w800", "김길동", "서버YB"),
        Info("https://i.pinimg.com/550x/c2/95/7d/c2957d833f37315e4dd4c51a34ce046c.jpg", "박길동", "아요YB")

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
            HorizontalItemDecoration(5f, 10f, 20, ContextCompat.getColor(requireContext(), R.color.divider_gray)
            )
        )

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

}