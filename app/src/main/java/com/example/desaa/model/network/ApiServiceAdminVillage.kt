package com.example.desaa.model.network

import com.example.desaa.model.response.*
import retrofit2.http.*

interface ApiServiceAdminVillage {

    @FormUrlEncoded
    @POST("login")
    suspend fun getToken(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseLogin

    @POST("logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): ResponseLogout

    @GET("statistik_desa")
    suspend fun getStatistics(@Header("Authorization") token: String): ResponseVillageStatsInfo

    @GET("aparatur_logged")
    suspend fun getAparatureogged(@Header("Authorization") token: String): ResponseAparatureLogged

    @GET("peraturan_desa")
    suspend fun getRuleVillage(@Header("Authorization") token: String): ResponseRuleVillage

    @GET("keputusan_kepala_desa")
    suspend fun getDecisionHeadman(@Header("Authorization") token: String): ResponseDecisionHeadman

    @GET("aparat_pemerintah_desa")
    suspend fun getVillageGovernmentOfficials(@Header("Authorization") token: String): ResponseVillageGovernmentOfficials

    @GET("aparatur")
    suspend fun getAparatureRole(@Header("Authorization") token: String): ResponseRoleLogged
}