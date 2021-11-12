package com.example.cardinfofinder.network

import com.example.cardinfofinder.domain.CardInfoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{id}")
    suspend fun getCardDetails (@Path("id") id: String): Response<CardInfoModel>

}