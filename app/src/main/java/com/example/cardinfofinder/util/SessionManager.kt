package com.example.cardinfofinder.util

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager (private val sharedPreferences: SharedPreferences) {

    /*Save details to Shared Preferences*/
    fun saveToSharedPref(prefType: String, prefValue: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(prefType, prefValue)
        editor.apply()
    }

    /*Load details From Shared Preferences*/
    fun loadFromSharedPref(prefType: String): String {
        return sharedPreferences.getString(prefType, "").toString()
    }
}
