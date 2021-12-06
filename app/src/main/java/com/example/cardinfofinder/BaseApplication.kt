package com.example.cardinfofinder

import android.app.Application
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.domain.HistoryModel
import com.example.cardinfofinder.util.SavedCardPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        SavedCardPreference.with(this)

    }
}