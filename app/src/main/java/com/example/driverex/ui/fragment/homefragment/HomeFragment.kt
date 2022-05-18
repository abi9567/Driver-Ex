package com.example.driverex.ui.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.driverex.enums.ApiStatus
import com.example.driverex.R
import com.example.driverex.constants.LOGIN_KEY
import com.example.driverex.databinding.FragmentHomeBinding
import com.example.driverex.extention.navigation
import com.example.driverex.extention.showToast
import com.example.driverex.ui.adapter.EmployeeAdapter
import com.example.driverex.utils.SharedPrefUtils


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel : EmployeeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBarHomePage.inflateMenu(R.menu.toolbar_home)

        binding.toolBarHomePage.setOnMenuItemClickListener {
            when(it.itemId)
            {
                R.id.menuLogout -> {settingLogOut()
                    true}
                else -> false
            }
        }
        settingUI()
    }

    private fun settingUI()
    {
        viewModel.employees.observe(viewLifecycleOwner) { apiResponse ->
            binding.progressBarHome.isVisible = apiResponse.apiStatus == ApiStatus.LOADING

            when (apiResponse.apiStatus) {
                ApiStatus.SUCCESS -> apiResponse.data.let { employeeResponse ->
                    binding.rvEmployee.apply {
                        adapter =  EmployeeAdapter(employeeResponse!!.sortedBy { it.firstName }) { employeeData ->
                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(employeeData))
                        }
                    }
                }
                ApiStatus.ERROR -> apiResponse.message.let { message->
                    requireContext().showToast(message!!)
                    Log.d("Error",message)
                }
                else -> {}
            }
        }
    }


    private fun settingLogOut()
    {
        SharedPrefUtils.setLogINOut("OUT")
        requireView().navigation(R.id.action_homeFragment_to_loagingFragment)
    }
}