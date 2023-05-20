package com.example.movieapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit {

        if (INSTANCE == null) {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()


            INSTANCE = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        }

        return INSTANCE!!
    }



}