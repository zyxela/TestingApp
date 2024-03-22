package com.example.testingapp.location

import android.net.Uri

interface TapItemListener {
    var addPosition:Int
    val position:MutableList<Int>
    fun getImageUri()
}