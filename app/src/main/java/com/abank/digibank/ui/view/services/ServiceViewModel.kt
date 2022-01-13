package com.abank.digibank.ui.view.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abank.digibank.R
import com.abank.digibank.app.MyApplication
import com.abank.digibank.data.model.ServicesModel
import com.abank.digibank.data.repository.APIRepository
import com.abank.digibank.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceViewModel constructor(private val apiRepository: APIRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val serviceList = MutableLiveData<List<ServicesModel>>()

    fun getAllServices() {
        //Check if internet is connected else throw error message
        if (Utils.checkForInternet()) {
            //Get services from remote server
            val response = apiRepository.getAllServices()
            response.enqueue(object : Callback<List<ServicesModel>> {
                override fun onResponse(
                    call: Call<List<ServicesModel>>,
                    response: Response<List<ServicesModel>>
                ) {
                    if (response.body()!!.isEmpty())
                        errorMessage.postValue(MyApplication.appContext.getString(R.string.error_msg_no_data))
                    else
                        serviceList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<ServicesModel>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        } else {
            errorMessage.postValue(MyApplication.appContext.getString(R.string.error_msg_no_internet))
        }
    }
}