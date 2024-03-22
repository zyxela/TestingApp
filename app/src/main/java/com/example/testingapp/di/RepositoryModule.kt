package com.example.testingapp.di

import com.example.testingapp.data.repository.LocationDBRepository
import com.example.testingapp.data.repositoryImpl.LocationDBRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getLDBRepo(repo: LocationDBRepositoryImpl): LocationDBRepository
}