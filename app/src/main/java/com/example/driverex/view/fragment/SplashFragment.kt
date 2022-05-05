package com.example.driverex.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSplashBinding
import com.example.driverex.utils.SharedPrefUtils
import com.example.driverex.utils.navigation
import com.example.driverex.viewmodel.EmployeeViewModel


class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding
    private lateinit var viewModel : EmployeeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)

        val check = viewModel.getSharedPrefLogin()


            when (check) {
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