package com.example.cardinfofinder.repository

import com.example.cardinfofinder.domain.CardInfoModel
import com.example.cardinfofinder.network.RetrofitService
import retrofit2.Response

class CardInfoRepositoryImpl(
    private val retrofitService: RetrofitService
    ) : CardInfoRepository {

    override suspend fun getCardDetails(id: String): Response<CardInfoModel> {
        return retrofitService.getCardDetails(id)
    }

}
