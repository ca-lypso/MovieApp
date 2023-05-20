package com.example.movieapp.daggerhilt

import com.example.movieapp.repository.Repository
import com.example.movieapp.retrofit.ApiUtils
import com.example.movieapp.retrofit.WebApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getRepostory(apiService: WebApiService): Repository {
        return Repository(apiService)
    }

    @Provides
    @Singleton
    fun getApiService(): WebApiService {
        return ApiUtils.instance
    }
}