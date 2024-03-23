package com.example.testingapp.location

import android.net.Uri
import com.example.testingapp.data.models.LocationResponse

interface TapItemListener {
    var addPosition:Int
    val position:MutableList<Int>
    var locationList:MutableList<LocationResponse>
    fun getImageUri()
}