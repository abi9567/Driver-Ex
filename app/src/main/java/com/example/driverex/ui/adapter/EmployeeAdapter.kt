package com.example.driverex.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.driverex.R
import com.example.driverex.databinding.EmployeeIndividualRowBinding
import com.example.driverex.data.model.EmployeeData

class EmployeeAdapter(val context: Context, val employeeList: List<EmployeeData>) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {


    class MyViewHolder (val binding : EmployeeIndividualRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.MyViewHolder {

        return MyViewHolder(EmployeeIndividualRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.MyViewHolder, position: Int) {

        val currentItem = employeeList[position]
        holder.binding.employeeIndividualName.text = context.getString(R.string.individual_name,currentItem.first_name,currentItem.last_name)

        holder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putParcelable("bundle",currentItem)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_userDetailsFragment,bundle)

        }

    }

    override fun getItemCount(): Int {
        return employeeList.size
    }




}




