package com.example.driverex.ui.fragment.userdetailfragment

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.driverex.R
import com.example.driverex.databinding.FragmentUserDetailsBinding
import com.example.driverex.extention.navigation
import com.example.driverex.extention.snackBar
import com.example.driverex.utils.SharedPrefUtils


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)

        val arguments : UserDetailsFragmentArgs by navArgs()


            when (SharedPrefUtils.getSharedPrefFavData()) {
                "YES" -> {
                    binding.favouriteButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
                    binding.favouriteButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
                }
                else -> {
                    binding.favouriteButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white))
                    binding.favouriteButton.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.black))
                }
            }



//        val bundle = arguments?.getParcelable<EmployeeData>("bundle")!!

        binding.userNameText.setText(getString(R.string.individual_name, arguments.data.firstName,arguments.data.lastName))

        binding.userMobileText.setText(arguments.data.mobileNumber.toString())
        binding.userWorkMobileText.setText(arguments.data.landLineNumber.toString())
        binding.personalEmailText.setText(arguments.data.email)


        binding.favouriteButton.setOnClickListener {

            SharedPrefUtils.favouriteSharedPref()
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
