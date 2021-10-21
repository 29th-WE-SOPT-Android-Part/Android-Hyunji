package com.example.androidseminar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidseminar.databinding.ItemFollowerBinding
import java.util.*


class FollowerRecyclerViewAdapter
    : RecyclerView.Adapter<FollowerRecyclerViewAdapter.MyViewHolder>(),
        MyTouchHelperCallback.OnItemMoveListener
{

    var infoList=mutableListOf<Info>()


    interface OnItemClickListener{
        fun onItemClick(v: View,info:Info,pos:Int)
    }
    private var listener:OnItemClickListener?=null
    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener=listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowerRecyclerViewAdapter.MyViewHolder {
        val binding=ItemFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int =infoList.size

    inner class MyViewHolder(private val binding: ItemFollowerBinding):RecyclerView.ViewHolder(binding.root){



        fun bind(info:Info){
            binding.nameTv.text=info.follower_name
            binding.partNameTv.text=info.follower_part
            binding.imageIv.setImageResource(info.follower_img)



            val pos=adapterPosition
            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,info,pos)
                }
            }
        }


    }

    interface OnStartDragListener{
        fun onStartDrag(viewHolder: MyViewHolder)
    }
    private var dragListener:OnStartDragListener?=null
    fun startDrag(listener:OnStartDragListener){
        this.dragListener=listener
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(infoList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun afterDragAndDrop(){
        notifyDataSetChanged()
    }

    fun removeTask(position: Int){
        infoList.removeAt(position)
        notifyItemRemoved(position)
    }


}