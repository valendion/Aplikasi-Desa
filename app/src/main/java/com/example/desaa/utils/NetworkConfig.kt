package com.example.desaa.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {

    private val timeOut = 30000L
    private val BASE_URL = ""

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.callTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.readTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.connectTimeout(timeOut, TimeUnit.MILLISECONDS)
        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    val apiServiceSector: ApiServiceSector by lazy {
//        retrofit.create(ApiServiceSector::class.java)
//    }


}