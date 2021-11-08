package com.example.androidseminar

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url:String){
        Glide.with(imageView.context).load(url)
            .circleCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}