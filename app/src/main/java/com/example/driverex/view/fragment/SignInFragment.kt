package com.example.driverex.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSignInBinding
import com.example.driverex.utils.navigation
import com.example.driverex.viewmodel.EmployeeViewModel
import com.example.driverex.utils.showToast
import com.example.driverex.utils.snackBar

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)


        //ViewModel Creation
        viewModel = ViewModelProvider(requireActivity()).get(EmployeeViewModel::class.java)


        binding.signInButton2.setOnClickListener { view ->

            if (binding.emailInput.text == null || binding.passwordInput.text == null) {
                requireContext().showToast("Enter Both Username & Password")
            }

            else {
                viewModel.login(binding.emailInput.text.toString(), binding.passwordInput.text.toString()).observe(viewLifecycleOwner) { loginResponse ->

                        viewModel.proceed.observe(viewLifecycleOwner) { proceed ->

                            if (proceed) {
                                if (findNavController().currentDestination?.id == R.id.signInFragment) {
                                   view.navigation(R.id.action_signInFragment_to_homeFragment)
                                    viewModel.setLogINOut("IN")
                                    viewModel.setSharedPrefToken(loginResponse.access_token)

//                                view.navigation()

                                    viewModel.loginMessage.observe(viewLifecycleOwner) {
                                        requireView().snackBar(it)
                                    }

                                }
                            }


                        }

                    }

                viewModel.errorResponse.observe(viewLifecycleOwner) {
                    requireContext().showToast(it.message)
                }

        }

            viewModel.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }




    }

            return binding.root
        }
    }

