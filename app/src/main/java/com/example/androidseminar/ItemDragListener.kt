package com.example.androidseminar

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}