package com.example.testingapp.data.models

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationResponse(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name="images")val images:ArrayList<String>
)
