package com.example.androidseminar.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.activity.DetailActivity
import com.example.androidseminar.data.Info
import com.example.androidseminar.databinding.ItemFollowerBinding
import com.example.androidseminar.util.ItemActionListener
import com.example.androidseminar.util.ItemDragListener


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding= ItemFollowerBinding.inflate(inflater,parent,false)
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

        fun bind(item: Info){

            with(binding){
                followerItem=item
                executePendingBindings()
            }

            itemView.setOnClickListener {
                val intent=Intent(itemView.context, DetailActivity::class.java)
                    .putExtra("name", item.followerName)
                    //.putExtra("picture", info.followerImg)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ContextCompat.startActivity(itemView.context,intent,null)
            }

        }

    }



}