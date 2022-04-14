package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataInfoVillage(
    @SerializedName("jumlah_penduduk")
    var jumlah_penduduk: Int? = null,
    @SerializedName("jumlah_penduduk_dewasa")
    var jumlah_penduduk_dewasa: Int? = null,
    @SerializedName("jumlah_penduduk_gol_darah_a")
    var jumlah_penduduk_gol_darah_a: Int? = null,
    @SerializedName("jumlah_penduduk_gol_darah_ab")
    var jumlah_penduduk_gol_darah_ab: Int? = null,
    @SerializedName("jumlah_penduduk_gol_darah_b")
    var jumlah_penduduk_gol_darah_b: Int? = null,
    @SerializedName("jumlah_penduduk_gol_darah_o")
    var jumlah_penduduk_gol_darah_o: Int? = null,
    @SerializedName("jumlah_penduduk_lk")
    var jumlah_penduduk_lk: Int? = null,
    @SerializedName("jumlah_penduduk_pr")
    var jumlah_penduduk_pr: Int? = null,
    @SerializedName("telepon_desa")
    var telepon_desa: String? = null,
    @SerializedName("website_desa")
    var website_desa: String? = null
)