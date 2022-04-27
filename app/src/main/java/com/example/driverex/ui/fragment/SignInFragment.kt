package com.example.driverex.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSignInBinding.inflate(layoutInflater,container,false)

        binding.signInButton2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signInFragment_to_homeFragment)
        }




        return binding.root
    }
}