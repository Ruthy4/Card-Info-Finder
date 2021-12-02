package com.example.cardinfofinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardinfofinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }
}