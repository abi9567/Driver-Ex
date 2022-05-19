package com.example.driverex.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.driverex.R
import com.example.driverex.databinding.ActivityMainBinding
import com.example.driverex.extention.showToast
import com.example.driverex.utils.SharedPrefUtils


class MainActivity : AppCompatActivity() {

    private var backPress: Boolean = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.driver_ex_navigation)


        installSplashScreen().apply {
            when (SharedPrefUtils.getSharedPrefLogin()) {
                "IN" -> {
                    navGraph.setStartDestination(R.id.homeFragment)
                }
                else -> {
                    navGraph.setStartDestination(R.id.loagingFragment)
                }
            }
            navController.graph = navGraph
        }

        setContentView(binding.root)

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
