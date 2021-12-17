package com.example.androidseminar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidseminar.R
import com.example.androidseminar.databinding.ActivityDetailBinding
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