package com.example.androidseminar.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.androidseminar.R
import com.example.androidseminar.databinding.ActivityOnboardingBinding
import com.example.androidseminar.util.BaseActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>({ ActivityOnboardingBinding.inflate(it)}) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_onboarding) as NavHostFragment
        val navController=navHostFragment.findNavController()
        setSupportActionBar(binding.tbTop)
        setupActionBarWithNavController(navController)


    }


}