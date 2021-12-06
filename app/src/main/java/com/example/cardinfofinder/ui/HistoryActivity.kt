package com.example.cardinfofinder.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.cardinfofinder.R
import com.example.cardinfofinder.databinding.ActivityHistoryBinding
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.domain.HistoryModel
import com.example.cardinfofinder.util.SavedCardPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        historyRecyclerView = binding.historyRv
        historyAdapter = HistoryAdapter()
        historyRecyclerView.adapter = historyAdapter
//        historyAdapter.submitList(list)




    }
}