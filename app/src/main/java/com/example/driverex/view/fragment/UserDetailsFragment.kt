package com.example.driverex.view.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.driverex.R
import com.example.driverex.databinding.FragmentUserDetailsBinding
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.utils.navigation
import com.example.driverex.utils.snackBar
import com.example.driverex.viewmodel.EmployeeViewModel


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var viewModel : EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)

        viewModel.getSharedPrefFavData()

        val favo = viewModel.getSharedPrefFavData()

            when (favo) {
                "YES" -> {
                    binding.favouriteButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
                    binding.favouriteButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
                }
                else -> {
                    binding.favouriteButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white))
                    binding.favouriteButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.black))
                }
            }



        val bundle = arguments?.getParcelable<EmployeeData>("bundle")!!

        binding.userNameText.setText(
            getString(
                R.string.individual_name,
                bundle.first_name,
                bundle.last_name
            )
        )
        binding.userMobileText.setText(bundle.mobile.toString())
        binding.userWorkMobileText.setText(bundle.landline.toString())
        binding.personalEmailText.setText(bundle.email)


        binding.favouriteButton.setOnClickListener {

            viewModel.favouriteSharedPref()
            binding.favouriteButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
            binding.favouriteButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
            it.snackBar("Added to Favorite")
        }

        binding.toolBarDetailPage.goBack.setOnClickListener {
            it.navigation(R.id.action_userDetailsFragment_to_homeFragment)
        }
            return binding.root
        }
    }
