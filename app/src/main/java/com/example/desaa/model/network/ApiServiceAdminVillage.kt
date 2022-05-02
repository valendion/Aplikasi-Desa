package com.example.desaa.model.network

import com.example.desaa.model.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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

    @GET("program_bantuan_sosial")
    suspend fun getHelpProgramList(): ResponseHelpProgramList

    @GET("daftar_pengajuan_surat_dusun")
    suspend fun getIntroductionVillageLetterList(): ResponseIntroductionVillageLetter

    @GET("peserta_bantuan_sosial/{id}")
    suspend fun getHelpProgramParticipant(@Path("id") id: Int): ResponseHelpProgramPartisipant

    @GET("pengajuan_surat_pengantar_dusun_belum_acc/{id}")
    suspend fun getIntroductionSubmissionNotAcc(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ResponseIntroductionSubmission

    @GET("daftar_jenis_surat")
    suspend fun getTypeLetter(): ResponseTypeLetter

    @GET("pengajuan_surat_pengantar_dusun_acc/{id}")
    suspend fun getIntroductionSubmissionAcc(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ResponseIntroductionSubmission

    @FormUrlEncoded
    @POST("pengajuan_surat_pengantar_dusun")
    suspend fun getMessageApprove(
        @Header("Authorization") token: String,
        @Field("wilayah_administratif_dusun_id") id: Int,
        @Field("nik") nik: String,
        @Field("jenis_surat_akan_dibuat") jenisSurat: String
    ): ResponseApprove

    @Multipart
    @POST("surat_pengantar_dusun")
    suspend fun postFormUser(
        @Part("nik") nik: RequestBody,
        @Part("jenis_surat_akan_dibuat") jenisSurat: RequestBody,
        @Part("keterangan") keterangan: RequestBody,
        @Part image: MultipartBody.Part
    ): ResponseApprove
}