package com.example.driverex.ui.fragment.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driverex.R
import com.example.driverex.data.model.EmployeeData
import com.example.driverex.databinding.FragmentHomeBinding
import com.example.driverex.extention.navigation
import com.example.driverex.ui.adapter.EmployeeAdapter
import com.example.driverex.viewmodel.EmployeeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider((requireActivity())).get(EmployeeViewModel::class.java)


        val token = viewModel.getSharedPrefAccessToken()


        viewModel.employeeData(token).observe(viewLifecycleOwner) {
            binding.employeeRecyclerView.apply {
                adapter = EmployeeAdapter(requireContext(), it.sortedBy { it.first_name })
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

        binding.include.logOut.setOnClickListener {
            viewModel.setLogINOut("OUT")
            requireView().navigation(R.id.action_homeFragment_to_loagingFragment)
        }


//       viewModel.employeeErrorResponse.observe(viewLifecycleOwner) {
//            if (it.message.equals("Unauthorized")) {
//                Log.d("ViewModel",it.message)
//                requireView().navigation(R.id.action_homeFragment_to_loagingFragment)
//            }
//        }



        return binding.root
    }

    fun navigation() {
        var data = EmployeeData(

        )
        findNavController().navigate(R.id.action_homeFragment_to_userDetailsFragment, data)
    }

}