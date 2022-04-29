package com.example.driverex.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.driverex.R
import com.example.driverex.databinding.FragmentUserDetailsBinding
import com.example.driverex.model.data.EmployeeData


class UserDetailsFragment : Fragment() {

    private lateinit var binding : FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater,container,false)

        val bundle = arguments?.getParcelable<EmployeeData>("bundle")!!

        binding.userNameText.setText(getString(R.string.individual_name,bundle.first_name,bundle.last_name))
        binding.userMobileText.setText(bundle.mobile.toString())
        binding.userWorkMobileText.setText(bundle.landline.toString())
        binding.personalEmailText.setText(bundle.email)

        return binding.root
    }
}