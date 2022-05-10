package com.example.driverex.ui.fragment.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driverex.R
import com.example.driverex.databinding.FragmentHomeBinding
import com.example.driverex.extention.navigation
import com.example.driverex.ui.adapter.EmployeeAdapter
import com.example.driverex.ui.fragment.homefragment.viewmodel.EmployeeViewModel
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

        binding.include.logOut.setOnClickListener {
            settingLogOut()
        }
        settingRecyclerView()
    }

    private fun settingRecyclerView() {
        viewModel.employeeData(SharedPrefUtils.getSharedPrefAccessToken()).observe(viewLifecycleOwner) {
            binding.employeeRecyclerView.apply {
                val employeeAdapter = EmployeeAdapter(it.sortedBy { it.firstName})
                { employeeData -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(employeeData)) }
                adapter = employeeAdapter
            }
        }
    }

    private fun settingLogOut() {
        SharedPrefUtils.setLogINOut("OUT")
        requireView().navigation(R.id.action_homeFragment_to_loagingFragment)
    }
}