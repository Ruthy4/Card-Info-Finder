package com.example.cardinfofinder.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SavedCardPreference {
     lateinit var preferences: SharedPreferences
    const val   PREFERENCE_FILE_NAME = "PREFERENCE FILE NAME"



    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(`object`:T, key:String) {

        val jsonString = GsonBuilder().create().toJson(`object`)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun<reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}