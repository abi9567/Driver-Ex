package com.example.driverex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driverex.R
import com.example.driverex.databinding.EmployeeIndividualRowBinding
import com.example.driverex.data.model.EmployeeData


class EmployeeAdapter(private val employeeList: List<EmployeeData>, val itemClick : ((EmployeeData) -> Unit)) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

    class MyViewHolder (val binding : EmployeeIndividualRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EmployeeIndividualRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = employeeList[position]
        holder.binding.employeeIndividualName.text = holder.itemView.context.getString(R.string.individual_name, currentItem.firstName, currentItem.lastName)

        holder.itemView.setOnClickListener {
            itemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

}




