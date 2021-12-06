package com.example.cardinfofinder.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cardinfofinder.databinding.HistoryRvItemBinding
import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.domain.HistoryModel
import com.example.cardinfofinder.util.SavedCardPreference
import com.example.cardinfofinder.util.SessionManager

class HistoryAdapter : ListAdapter<HistoryModel, HistoryAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: HistoryRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var sessionManager: SessionManager

        fun bind(historyModel: HistoryModel) {
            val result =  SavedCardPreference.get<CardInfoModel>("KEY_CARD_INFO")
            val cardNumber = sessionManager.loadFromSharedPref("CARD_NUMBER")

            Log.d("Result", "bind: $result")

            binding.cardNumberTv.text = cardNumber
            binding.visaTextView.text = result?.type

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HistoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object DiffCallback : DiffUtil.ItemCallback<HistoryModel>() {
    override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem == newItem
    }
}

