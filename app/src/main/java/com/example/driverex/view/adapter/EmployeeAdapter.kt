package com.example.driverex.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.driverex.R
import com.example.driverex.databinding.EmployeeIndividualRowBinding
import com.example.driverex.model.data.EmployeeData
import com.example.driverex.model.data.EmployeeResponse

class EmployeeAdapter(val context : Context, val employeeList : List<EmployeeData>) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {


    class MyViewHolder (val binding : EmployeeIndividualRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeAdapter.MyViewHolder {

        return MyViewHolder(EmployeeIndividualRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.MyViewHolder, position: Int) {

        val currentItem = employeeList[position]
        holder.binding.employeeIndividualName.text = context.getString(R.string.individual_name,currentItem.first_name,currentItem.last_name)

    }

    override fun getItemCount(): Int {
        return employeeList.size
    }


}



