package com.example.testingapp.data.repository

import com.example.testingapp.data.models.LocationResponse

interface LocationDBRepository {
    suspend fun getLocations(): List<LocationResponse>

    suspend fun insertItem(locationResponse: LocationResponse)

    suspend fun deleteAll()
}