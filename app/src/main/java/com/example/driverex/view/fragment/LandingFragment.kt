package com.example.driverex.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.driverex.R
import com.example.driverex.databinding.FragmentLandingBinding


class LandingFragment : Fragment() {

    private lateinit var binding : FragmentLandingBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLandingBinding.inflate(layoutInflater,container,false)

        binding.signInButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loagingFragment_to_signInFragment)
        }


        return binding.root
    }
}