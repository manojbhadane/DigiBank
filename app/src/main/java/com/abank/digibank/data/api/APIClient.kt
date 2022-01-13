package com.abank.digibank.data.api

import com.abank.digibank.data.model.ServicesModel
import com.abank.digibank.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIClient {

    @GET("services.json")
    fun getAllServices(): Call<List<ServicesModel>>

    companion object {

        var apiClient: APIClient? = null

        fun getInstance() : APIClient {

            if (apiClient == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiClient = retrofit.create(APIClient::class.java)
            }
            return apiClient!!
        }
    }
}