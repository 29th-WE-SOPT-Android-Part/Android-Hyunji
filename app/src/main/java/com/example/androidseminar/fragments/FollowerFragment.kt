package com.example.androidseminar.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.R
import com.example.androidseminar.adapter.FollowerRecyclerViewAdapter
import com.example.androidseminar.api.GithubCreator
import com.example.androidseminar.data.Info
import com.example.androidseminar.data.ResponseGithubFollowerData
import com.example.androidseminar.databinding.FragmentFollowerBinding
import com.example.androidseminar.util.*
import com.example.androidseminar.util.function.HorizontalItemDecoration
import com.example.androidseminar.util.function.ItemDragListener
import com.example.androidseminar.util.function.ItemTouchHelperCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : ItemDragListener, BaseFragment<FragmentFollowerBinding>() {

    private lateinit var adapter: FollowerRecyclerViewAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    val followerData = mutableListOf<Info>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFollowerBinding {
        return FragmentFollowerBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initGithubFollowerNetwork()
        initFollowerRecyclerView()
        Log.d("followerData",followerData.toString())
    }

    private fun initFollowerRecyclerView() {
        adapter = FollowerRecyclerViewAdapter(this)
        //여기다가 adapter.infoList = followerData,adapter.notifyDataSetChanged() 해줬을땐 안됨!
        binding.followerRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecyclerview)
        binding.followerRecyclerview.adapter = adapter

        //recyclerview 아이템 간격 조절
        binding.followerRecyclerview.addItemDecoration(
            HorizontalItemDecoration(5f, 10f, 20, ContextCompat.getColor(requireContext(),
                R.color.divider_gray
            )
            )
        )
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    private fun initGithubFollowerNetwork(){
        val call: Call<List<ResponseGithubFollowerData>> = GithubCreator.githubService.getGithubFollowers("hyunji24")

        call.enqueue(object: Callback<List<ResponseGithubFollowerData>> {
            override fun onResponse(
                call: Call<List<ResponseGithubFollowerData>>,
                response: Response<List<ResponseGithubFollowerData>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        it.forEach { follower ->
                            Log.d("Answers",follower.toString())
                            followerData.add(Info(follower.profileImg,follower.id,"안드로이드"))
                        }
                        adapter.infoList = followerData // TODO 왜 여기 위치해야함
                        adapter.notifyDataSetChanged()

                    }
                }else {
                    Toast.makeText(requireContext(), "깃허브 팔로워 조회 실패", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseGithubFollowerData>>, t: Throwable) {
                Toast.makeText(requireContext(), "서버 에러", Toast.LENGTH_LONG).show()
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}