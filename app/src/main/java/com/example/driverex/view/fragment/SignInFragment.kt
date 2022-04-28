package com.example.driverex.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.driverex.databinding.FragmentSignInBinding
import com.example.driverex.viewmodel.EmployeeViewModel

class SignInFragment : Fragment() {

    private lateinit var binding : FragmentSignInBinding
    private lateinit var viewModel : EmployeeViewModel
    private lateinit var sharedPref : SharedPreferences

    val KEY = "MYPREF"
    val ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        sharedPref = requireContext().getSharedPreferences(KEY,Context.MODE_PRIVATE)

        //ViewModel Creation
        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)


        binding.signInButton2.setOnClickListener{


            }



        return binding.root
    }
}