package com.example.driverex.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.driverex.databinding.FragmentHomeBinding
import com.example.driverex.view.adapter.EmployeeAdapter
import com.example.driverex.viewmodel.EmployeeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : EmployeeViewModel
    private lateinit var sharedPref : SharedPreferences

    val KEY = "MYPREF"
    val ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider((requireActivity())).get(EmployeeViewModel::class.java)
        sharedPref = requireContext().getSharedPreferences(KEY,Context.MODE_PRIVATE)

        val token = sharedPref.getString(ACCESS_TOKEN,null)
        viewModel.employeeData(token.toString())


        viewModel.repository.employeeDetails.observe(viewLifecycleOwner) {

            binding.employeeRecyclerView.apply {

                adapter = EmployeeAdapter(requireContext(),it.sortedBy { it.first_name })

                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }

        return binding.root
    }
}