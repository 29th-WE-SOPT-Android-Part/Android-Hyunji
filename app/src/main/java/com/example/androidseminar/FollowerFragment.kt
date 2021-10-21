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
import com.example.androidseminar.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {

    private lateinit var adapter:FollowerRecyclerViewAdapter
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var callback: MyTouchHelperCallback
    private lateinit var touchHelper: ItemTouchHelper


    val followerData=mutableListOf<Info>(
        Info(R.drawable.github_icon,"윤현지","안드YB"),
        Info(R.drawable.github_icon,"홍길동","안드OB"),
        Info(R.drawable.github_icon,"김길동","서버YB"),
        Info(R.drawable.github_icon,"박길동","아요YB")

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentFollowerBinding.inflate(inflater,container,false)




        initFollowerRecyclerView()

        adapter.setOnItemClickListener(object:FollowerRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, info: Info, pos: Int) {
                Intent(v.context,DetailActivity::class.java)
                    .putExtra("name",info.follower_name)
                    .putExtra("picture",info.follower_img)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .run{startActivity(this)}
            }
        })



        return binding.root
    }



    private fun initFollowerRecyclerView(){
        adapter= FollowerRecyclerViewAdapter()
        adapter.infoList=followerData

        binding.followerRecyclerview.layoutManager=LinearLayoutManager(requireContext())

        //아이템 드래그 시 이동하도록
        callback= MyTouchHelperCallback(adapter,adapter)
        touchHelper= ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.followerRecyclerview)
        binding.followerRecyclerview.adapter=adapter
        adapter.startDrag(object : FollowerRecyclerViewAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: FollowerRecyclerViewAdapter.MyViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })



        //recyclerview 아이템 간격 조절
        binding.followerRecyclerview.addItemDecoration(HorizontalItemDecoration(10f,10f,20, ContextCompat.getColor(requireContext(),R.color.hot_pink)))

    }

}