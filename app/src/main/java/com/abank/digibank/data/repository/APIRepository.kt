package com.abank.digibank.data.repository

import com.abank.digibank.data.api.APIClient

class APIRepository constructor(private val apiClient: APIClient) {

    fun getAllServices() = apiClient.getAllServices()
}