package com.example.driverex.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.driverex.MyApplication
import com.example.driverex.R
import com.example.driverex.databinding.ActivityMainBinding
import com.example.driverex.utils.showToast


class MainActivity : AppCompatActivity() {

    private var backPress: Boolean = false
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backPress = false

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
