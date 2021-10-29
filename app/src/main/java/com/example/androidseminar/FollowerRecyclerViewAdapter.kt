package com.example.androidseminar

import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidseminar.databinding.ItemFollowerBinding
import java.util.*


class FollowerRecyclerViewAdapter(private val listener: ItemDragListener)
    : RecyclerView.Adapter<FollowerRecyclerViewAdapter.MyViewHolder>(),
        ItemActionListener
{

    var infoList=mutableListOf<Info>()

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = infoList.removeAt(from)
        infoList.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        infoList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        //FollowerRecyclerViewAdapter.MyViewHolder -> redundant qualifier name 이라 해서 ~Adapter. 지움
        val binding=ItemFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(infoList[position])


    }

    override fun getItemCount()=infoList.size

    class MyViewHolder(private val binding: ItemFollowerBinding,listener: ItemDragListener)
        :RecyclerView.ViewHolder(binding.root){

        init {
            binding.dragHandle.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

        fun bind(info:Info){
            binding.nameTv.text=info.followerName
            binding.partNameTv.text=info.followerPart
            //binding.imageIv.setImageResource(info.followerImg)

            Glide.with(itemView?.context) //TODO 이거 itemView?.context맞는지..
                .load("https://yt3.ggpht.com/ytc/AKedOLTBmVN3RYeIJpA6Rlmx1vloR3PGaDYR6sfoCTb4=s900-c-k-c0x00ffffff-no-rj")
                .circleCrop()
                .into(binding.imageIv)

            itemView.setOnClickListener {
                val intent=Intent(itemView?.context,DetailActivity::class.java)
                    .putExtra("name", info.followerName)
                    .putExtra("picture", info.followerImg)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ContextCompat.startActivity(itemView.context,intent,null)
            }

        }

    }



}