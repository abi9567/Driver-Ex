package com.example.driverex.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.driverex.databinding.ActivityMainBinding
import com.example.driverex.extention.showToast



class MainActivity : AppCompatActivity() {

    private var backPress: Boolean = false
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        installSplashScreen()
    }

    override fun onBackPressed() {

        if (backPress) {
            super.onBackPressed()
        }
        backPress = true
        showToast("Press Again to Exit")

        Handler(Looper.getMainLooper()).postDelayed({
            backPress = false
        }, 2000)

    }

    }
