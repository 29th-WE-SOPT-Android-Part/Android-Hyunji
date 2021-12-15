package com.example.androidseminar.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.androidseminar.databinding.FragmentImageBinding
import com.example.androidseminar.util.BaseFragment

class ImageFragment : BaseFragment<FragmentImageBinding>() {


    private lateinit var filterActivityLauncher: ActivityResultLauncher<Intent>

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentImageBinding {
        return FragmentImageBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLauncher()
        openGallery()
    }

    private fun openGallery() {
        binding.btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            var galleryPermission = ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (galleryPermission == PackageManager.PERMISSION_GRANTED) {
                filterActivityLauncher.launch(intent)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE
                )
            }
        }
    }

    private fun initLauncher() {
        filterActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val imageUri = it.data?.data
                Glide.with(requireActivity())
                    .load(imageUri)
                    .into(binding.imageIv)
            }
    }

    companion object {
        const val REQUEST_CODE = 1
    }

}