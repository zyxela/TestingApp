package com.example.testingapp.data.repositoryImpl

import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.data.repository.LocationDBRepository
import com.example.testingapp.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationDBRepositoryImpl @Inject constructor(private val db: AppDatabase) :
    LocationDBRepository {
    override suspend fun getLocations(): List<LocationResponse> = withContext(Dispatchers.IO){
        return@withContext db.locationDao().getAll()
    }

    override suspend fun insertItem(locationResponse: LocationResponse) = withContext(Dispatchers.IO){
        db.locationDao().insertAll(locationResponse)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO){
        db.locationDao().deleteAll()
    }
}