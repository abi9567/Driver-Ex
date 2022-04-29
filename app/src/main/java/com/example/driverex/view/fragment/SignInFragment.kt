package com.example.driverex.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSignInBinding
import com.example.driverex.utils.navigation
import com.example.driverex.utils.sharedPref
import com.example.driverex.viewmodel.EmployeeViewModel
import com.example.driverex.utils.showToast

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: EmployeeViewModel
    private lateinit var sharedPref: SharedPreferences

    val KEY = "MYPREF"
    val ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)

        sharedPref = requireContext().sharedPref()

        //ViewModel Creation
        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)


        binding.signInButton2.setOnClickListener { view ->

            viewModel.login(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )

            viewModel.repository.loginResponse.observe(viewLifecycleOwner) {

                sharedPref.edit()
                    .putString(getString(R.string.sharedPrefAccessToken),it.access_token)
                    .putString(getString(R.string.sharedPrefLogCheck),getString(R.string.sharedPrefIn))
                    .apply()

                requireView().navigation(R.id.action_signInFragment_to_homeFragment)

            }

            viewModel.repository.errorResponse.observe(viewLifecycleOwner) {
                requireContext().showToast(it.message)
            }

            viewModel.repository.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else {
                    binding.progressBar.visibility = View.GONE
                }
            }

        }

            return binding.root
        }
    }
