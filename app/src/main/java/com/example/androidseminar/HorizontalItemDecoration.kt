package com.example.androidseminar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

//그냥참고 https://rkdxowhd98.tistory.com/95

class HorizontalItemDecoration( private val height: Float, private val padding: Float, private val divHeight:Int, @ColorInt private val color: Int ) : RecyclerView.ItemDecoration() {
    private val paint = Paint()
    init { paint.color = color }


    //간격 조절 : https://yunaaaas.tistory.com/43
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top=divHeight
        outRect.bottom=divHeight
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height
            c.drawRect(left, top, right, bottom, paint)
        }
    }
}



//
//class HorizontalItemDecoration(
//    context: Context,
//    resId: Int,
//    val paddingLeft: Int,
//    val paddingRight: Int
//) : RecyclerView.ItemDecoration() {
//    private var mDivider: Drawable? = null
//    init {
//        mDivider = ContextCompat.getDrawable(context, resId)
//    }
//    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        val left = parent.paddingLeft + paddingLeft
//        val right = parent.width - parent.paddingRight - paddingRight
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val child = parent.getChildAt(i)
//            val params = child.layoutParams as RecyclerView.LayoutParams
//            val top = child.bottom + params.bottomMargin
//            val bottom = top + (mDivider?.intrinsicHeight ?: 0)
//            mDivider?.let {
//                it.setBounds(left, top, right, bottom)
//                it.draw(c)
//            }
//        }
//    }
//}



//https://antilog.tistory.com/48
//class HorizontalItemDecoration (
////그릴 divider의 높이와 색상을 받는다
//private val dividerHeight: Int,
//private val dividerColor: Int = Color.TRANSPARENT ) : RecyclerView.ItemDecoration()
//
//    {
//        private val paint = Paint()
//
//        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//            myDivider(c,parent,color = dividerColor)
//        }
//
//        private fun myDivider(c: Canvas, parent: RecyclerView, color: Int) {
//            paint.color = color
//            for (i in 0 until parent.childCount) {
//                val child = parent.getChildAt(i)
//                val param = child.layoutParams as RecyclerView.LayoutParams
//                val dividerTop = child.bottom + param.bottomMargin
//                val dividerBottom = dividerTop + dividerHeight
//                c.drawRect( child.left.toFloat(), dividerTop.toFloat(), child.right.toFloat(), dividerBottom.toFloat(), paint ) }
//        }
//
//        override fun getItemOffsets(outRect: Rect,
//                                    view: View,
//                                    parent: RecyclerView,
//                                    state: RecyclerView.State )
//        {
//            outRect.bottom = dividerHeight
//        }
//}





