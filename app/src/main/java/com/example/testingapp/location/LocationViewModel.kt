package com.example.testingapp.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingapp.data.models.LocationResponse
import com.example.testingapp.data.repository.LocationDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationDBRepository) :
    ViewModel() {

    val locations = MutableLiveData<List<LocationResponse>?>(null)

    fun saveData(data: List<LocationResponse>) {
        viewModelScope.launch {
            repository.deleteAll()

                repository.insertItem(data)



        }
    }

    fun clear(){
        viewModelScope.launch {
            repository.deleteAll()
        }

    }

    fun getData() {
        viewModelScope.launch {
            locations.postValue(repository.getLocations())
        }
    }
}