package com.example.androidseminar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidseminar.databinding.ActivityDetailBinding
import com.example.androidseminar.databinding.ActivityOnboardingBinding
import com.example.androidseminar.util.BaseActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>({ ActivityOnboardingBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}