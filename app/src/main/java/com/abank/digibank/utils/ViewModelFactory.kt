package com.abank.digibank.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abank.digibank.data.repository.APIRepository
import com.abank.digibank.ui.view.services.ServiceViewModel

/**
 * ViewModelFactory is use to create instance ViewModel with argument
 */
class ViewModelFactory constructor(private val apiRepository: APIRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            ServiceViewModel(this.apiRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}