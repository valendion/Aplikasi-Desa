package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataVillageGovernmentOfficials(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("nama_pegawai_desa")
    var namaPegawaiDesa: String? = null,

    @SerializedName("nip")
    var nip: String? = null,

    @SerializedName("foto")
    var foto: String? = null,

    @SerializedName("jabatan")
    var jabatan: String? = null
)
