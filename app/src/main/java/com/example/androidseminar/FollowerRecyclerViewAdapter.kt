package com.example.androidseminar

import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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


    interface OnItemClickListener{
        fun onItemClick(v: View,info:Info,pos:Int)
    }
    private var clickListener:OnItemClickListener?=null
    fun setOnItemClickListener(clickListener:OnItemClickListener){
        this.clickListener=clickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerRecyclerViewAdapter.MyViewHolder {
        val binding=ItemFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: FollowerRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount()=infoList.size

    inner class MyViewHolder(private val binding: ItemFollowerBinding,listener: ItemDragListener):RecyclerView.ViewHolder(binding.root){


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
            binding.imageIv.setImageResource(info.followerImg)

            val pos=adapterPosition
            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    clickListener?.onItemClick(itemView,info,pos)
                }
            }
        }


    }



}