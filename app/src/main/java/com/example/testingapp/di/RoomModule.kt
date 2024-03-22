package com.example.testingapp.di

import android.content.Context
import androidx.room.Room
import com.example.testingapp.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext applicationContext:Context): AppDatabase {
       return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "locdatabase"
        ).build()
    }
}