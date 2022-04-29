package com.example.driverex.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSplashBinding
import com.example.driverex.utils.navigation
import com.example.driverex.utils.sharedPref


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding
    private lateinit var sharedPref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)

        sharedPref = requireContext().sharedPref()


        val loginCheck = sharedPref.getString(getString(R.string.sharedPrefLogCheck),null)

        when (loginCheck) {

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