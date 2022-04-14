package com.example.desaa.model.network

import com.example.desaa.model.response.ResponseLogin
import com.example.desaa.model.response.ResponseRuleVillage
import com.example.desaa.model.response.ResponseVillageStatsInfo
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceAdminVillage {
    @POST("login") suspend fun getToken(username: String, password: String): ResponseLogin
    @GET("statistik_desa") suspend fun getStatistics(): ResponseVillageStatsInfo
    @GET("aparatur_logged") suspend fun getAparatureogged(): ResponseVillageStatsInfo
    @GET("peraturan_desa") suspend fun getRuleVillage(): ResponseRuleVillage
    @GET("keputusan_kepala_desa") suspend fun getDecisionHeadman(): ResponseRuleVillage
    @GET("aparat_pemerintah_desa") suspend fun getVillageGovernmentOfficials(): ResponseRuleVillage
    @GET("aparatur") suspend fun get(): ResponseRuleVillage

}