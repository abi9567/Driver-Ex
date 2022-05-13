package com.example.driverex.ui.fragment.signinfragment

import android.os.Bundle
import android.util.Log
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

        binding.btnSIgnIn.setOnClickListener {
            signIn()
            isLoading()
            errorResponse()
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
            Log.d("SignError",it)
            requireContext().showToast(it)
        }
    }


    private fun signIn()
    {
        viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString()).observe(viewLifecycleOwner)
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

