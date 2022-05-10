package com.example.driverex.ui.fragment.splashfrag

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSplashBinding
import com.example.driverex.extention.navigation
import com.example.driverex.utils.SharedPrefUtils


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)

            when (SharedPrefUtils.getSharedPrefLogin()) {
                "IN" ->
                    Handler(Looper.getMainLooper()).postDelayed({
                        requireView().navigation(R.id.action_splashFragment_to_homeFragment)
                    },3000)

                else ->
                    Handler(Looper.getMainLooper()).postDelayed({
                        requireView().navigation(R.id.action_splashFragment_to_loagingFragment)
                    },3000)

            }




        return binding.root

    }
}