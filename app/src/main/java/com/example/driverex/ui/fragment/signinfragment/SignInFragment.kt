package com.example.driverex.ui.fragment.signinfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.driverex.enums.ApiStatus
import com.example.driverex.R
import com.example.driverex.databinding.FragmentSignInBinding
import com.example.driverex.extention.navigation
import com.example.driverex.extention.showToast
import com.example.driverex.utils.SharedPrefUtils


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel : SignInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d("Signin","Created")
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)

            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSIgnIn.setOnClickListener {
                signIn()
        }
    }


    private fun signIn()
    {
        viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString()).observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse.apiStatus)
            {
                ApiStatus.SUCCESS -> apiResponse.data.let { loginResponse ->
                    if (findNavController().currentDestination?.id == R.id.signInFragment) {
                        SharedPrefUtils.setLogINOut("IN")
                        SharedPrefUtils.setSharedPrefToken(loginResponse?.body()?.defaultData?.accessToken!!)
                        requireView().navigation(R.id.action_signInFragment_to_homeFragment)
                        binding.progressBar.visibility = View.GONE } }

                ApiStatus.ERROR -> apiResponse.message.let { message->
                    requireContext().showToast(message!!)
                    binding.progressBar.visibility = View.GONE }

                ApiStatus.LOADING ->
                {
                    binding.progressBar.visibility = View.VISIBLE
                }

            }
        }

    }

}



