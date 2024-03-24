package com.example.testingapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

class ArrayListConverter {
    @TypeConverter
    fun fromStringArrayList(value: ArrayList<ByteArray>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<ByteArray> {
        return try {
            Gson().fromJson<ArrayList<ByteArray>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}