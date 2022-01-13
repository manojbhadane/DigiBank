package com.abank.digibank.ui.view.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abank.digibank.data.model.ServicesModel
import com.abank.digibank.databinding.ListitemSevicesBinding
import com.abank.digibank.interfaces.OnItemClickListener

class ServiceListAdapter(
    private val serviceList: List<ServicesModel>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ServiceListAdapter.ServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val binding = ListitemSevicesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ServicesViewHolder(binding)
    }

    override fun getItemCount() = serviceList.size

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        with(holder) {
            with(serviceList[position]) {
                binding.txtService.text = name
                binding.parent.setOnClickListener {
                    listener.onItemClick(serviceList[position])
                }
            }
        }
    }

    inner class ServicesViewHolder(val binding: ListitemSevicesBinding) :
        RecyclerView.ViewHolder(binding.root)

}