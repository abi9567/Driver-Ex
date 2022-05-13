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
    private val arguments : UserDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        binding.toolBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        binding.toolBar.navigationIcon?.setTint(ContextCompat.getColor(requireContext(),R.color.white))
        binding.toolBar.setNavigationOnClickListener {
            it.navigation(R.id.action_userDetailsFragment_to_homeFragment)
        }


        return binding.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fabFavourite.setOnClickListener {
            FavButtonClick()
        }
        updateUI()
        favButton()
    }

    private fun FavButtonClick() {
        SharedPrefUtils.favouriteSharedPref()
        binding.fabFavourite.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
        binding.fabFavourite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
        view?.snackBar("Added to Favorite")
    }

    private fun favButton() {
        when (SharedPrefUtils.getSharedPrefFavData()) {
            "YES" -> {
                binding.fabFavourite.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
                binding.fabFavourite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.app_color))
            }
            else -> {
                binding.fabFavourite.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.white))
                binding.fabFavourite.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.black))
            }
        }
    }

    private fun updateUI() {
        binding.tvName.setText(getString(R.string.name, arguments.data.firstName,arguments.data.lastName))
        binding.tvMobileNum.setText(arguments.data.mobileNumber.toString())
        binding.tvOfficeNum.setText(arguments.data.landLineNumber.toString())
        binding.tvPersonalEmail.setText(arguments.data.email)
    }
}
