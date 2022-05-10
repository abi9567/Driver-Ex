package com.example.driverex.ui.fragment.signinfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSignInBinding
import com.example.driverex.extention.navigation
import com.example.driverex.extention.showToast
import com.example.driverex.extention.snackBar
import com.example.driverex.ui.fragment.signinfragment.viewmodel.SignInViewModel
import com.example.driverex.utils.SharedPrefUtils


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel : SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton2.setOnClickListener {
            checkEmpty()
            errorResponse()
            isLoading()
        }
    }

    private fun isLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner)
        {
            if (it) { binding.progressBar.visibility = View.VISIBLE }
            else { binding.progressBar.visibility = View.GONE }
        }
    }

    private fun errorResponse() {
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            requireContext().showToast(it.errorMessage)
        }
    }

    private fun checkEmpty()
    {
        if (binding.emailInput.text == null || binding.passwordInput.text == null)
        {
            requireContext().showToast("Enter Both Username & Password")
        }
        else
        {
            signIn()
        }
    }

    private fun signIn()
    {
        viewModel.login(binding.emailInput.text.toString(), binding.passwordInput.text.toString()).observe(viewLifecycleOwner)
        { loginResponse ->
            viewModel.proceed.observe(viewLifecycleOwner)

            { proceed ->
                if (proceed)
                {
                    if (findNavController().currentDestination?.id == R.id.signInFragment)
                    {
                        view?.navigation(R.id.action_signInFragment_to_homeFragment)
                        SharedPrefUtils.setLogINOut("IN")
                        SharedPrefUtils.setSharedPrefToken(loginResponse.accessToken)
                        viewModel.loginMessage.observe(viewLifecycleOwner) { requireView().snackBar(it) }
                    }
                }
            }
        }
    }
}

