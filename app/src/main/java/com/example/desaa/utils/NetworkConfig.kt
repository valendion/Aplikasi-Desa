package com.example.desaa.utils

import com.example.desaa.model.network.ApiServiceAdminVillage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfig {

    private const val timeOut = 100000L
    private const val BASE_URL = "https://testing.sunistheworld.com/api/"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val retrofit: Retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        httpClient.callTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.readTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(timeOut, TimeUnit.MILLISECONDS)
        httpClient.connectTimeout(timeOut, TimeUnit.MILLISECONDS)
         Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiServiceAdminVillage : ApiServiceAdminVillage by lazy {
        retrofit.create(ApiServiceAdminVillage::class.java)
    }

//    val apiServiceSector: ApiServiceSector by lazy {
//        retrofit.create(ApiServiceSector::class.java)
//    }


}