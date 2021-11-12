package com.example.cardinfofinder.di

import com.example.cardinfofinder.network.RetrofitService
import com.example.cardinfofinder.repository.CardInfoRepository
import com.example.cardinfofinder.repository.CardInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesCardInfoRepository(
        retrofitService : RetrofitService
    ): CardInfoRepository{
        return CardInfoRepositoryImpl(retrofitService)
    }
}