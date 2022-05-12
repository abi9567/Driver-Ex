package com.example.driverex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driverex.R
import com.example.driverex.data.model.EmployeeData
import com.example.driverex.databinding.EmployeeListItemBinding


class EmployeeAdapter(private val employeeList: List<EmployeeData>, val itemClick : ((EmployeeData) -> Unit)) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

    class MyViewHolder (val binding : EmployeeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EmployeeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = employeeList[position]
        holder.binding.tvEmployeeName.text = holder.itemView.context.getString(R.string.name, currentItem.firstName, currentItem.lastName)

        holder.itemView.setOnClickListener {
            itemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

}




