package com.abank.digibank.ui.view.services

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abank.digibank.R
import com.abank.digibank.data.api.APIClient
import com.abank.digibank.data.model.ServicesModel
import com.abank.digibank.data.repository.APIRepository
import com.abank.digibank.databinding.ActivityServicesBinding
import com.abank.digibank.interfaces.OnItemClickListener
import com.abank.digibank.ui.base.BaseActivity
import com.abank.digibank.utils.ViewModelFactory

class ServicesActivity : BaseActivity<ActivityServicesBinding>() {

    private val apiClient = APIClient.getInstance()
    private lateinit var viewModel: ServiceViewModel
    private lateinit var serviceListAdapter: ServiceListAdapter
    private var serviceList: ArrayList<ServicesModel> = ArrayList()

    override val layoutResId: Int
        get() = R.layout.activity_services

    override fun init(binding: ActivityServicesBinding?) {

        viewModel =
            ViewModelProvider(this, ViewModelFactory(APIRepository(apiClient))).get(
                ServiceViewModel::class.java
            )

        //initialise and set list adapter
        serviceListAdapter = ServiceListAdapter(serviceList, object : OnItemClickListener {
            override fun onItemClick(model: ServicesModel) {
                Toast.makeText(this@ServicesActivity, model.name, Toast.LENGTH_LONG).show()
            }
        })
        binding!!.recyclerviewServices.adapter = serviceListAdapter

        //Observe list changes
        viewModel.serviceList.observe(this, {
            binding.progressBar.visibility = View.GONE
            binding.txtErrorMsg.visibility = View.GONE
            serviceList.addAll(it)
            serviceListAdapter.notifyItemRangeInserted(0, serviceList.size)
        })

        //Observe error messages
        viewModel.errorMessage.observe(this, {
            binding.progressBar.visibility = View.GONE
            binding.txtErrorMsg.visibility = View.VISIBLE
            binding.txtErrorMsg.text = it
        })

        //Get services from API
        viewModel.getAllServices()
    }
}