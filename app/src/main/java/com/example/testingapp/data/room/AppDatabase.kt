package com.example.testingapp.data.room

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.utils.ArrayListConverter

@Database(entities = [LocationResponse::class], version = 1)
@TypeConverters(ArrayListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun locationDao():LocationDao
}