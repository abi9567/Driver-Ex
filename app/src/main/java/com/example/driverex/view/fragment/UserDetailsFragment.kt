package com.example.driverex.view.fragment

import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.driverex.R
import com.example.driverex.databinding.FragmentUserDetailsBinding
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.utils.sharedPref
import com.example.driverex.utils.snackBar
import com.google.android.material.snackbar.Snackbar


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        sharedPref = requireContext().sharedPref()


        val fav = sharedPref.getString(getString(R.string.sharedPrefFavKey), null)

        when (fav) {
            "YES" -> {
                binding.favouriteButton.backgroundTintList =
                    ColorStateList.valueOf(requireContext().resources.getColor(R.color.app_color))
                binding.favouriteButton.imageTintList =
                    ColorStateList.valueOf(requireContext().resources.getColor(R.color.app_color))
            }
            else -> {
                binding.favouriteButton.backgroundTintList =
                    ColorStateList.valueOf(requireContext().resources.getColor(R.color.white))
                binding.favouriteButton.imageTintList =
                    ColorStateList.valueOf(requireContext().resources.getColor(R.color.black))
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


            val g = sharedPref.edit()
                .putString(getString(R.string.sharedPrefFavKey),"YES")
                .apply()

            binding.favouriteButton.backgroundTintList =
                ColorStateList.valueOf(requireContext().resources.getColor(R.color.app_color))
            binding.favouriteButton.imageTintList =
                ColorStateList.valueOf(requireContext().resources.getColor(R.color.app_color))

            it.snackBar("Added to Favorite")

            val favo = 1




        }

            return binding.root
        }
    }
