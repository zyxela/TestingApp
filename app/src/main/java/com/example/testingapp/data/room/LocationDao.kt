package com.example.testingapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testingapp.data.models.LocationResponse

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationresponse")
    fun getAll(): List<LocationResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(locationResponse: LocationResponse)

    @Delete
    fun delete(locationResponse: LocationResponse)

    @Query("DELETE FROM locationresponse")
    fun deleteAll()

}