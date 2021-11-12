package com.example.cardinfofinder.repository

import com.example.cardinfofinder.domain.CardInfoModel
import retrofit2.Response

interface CardInfoRepository {
    suspend fun getCardDetails(id: String): Response<CardInfoModel>
}