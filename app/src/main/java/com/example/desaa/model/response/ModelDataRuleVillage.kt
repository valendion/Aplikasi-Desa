package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataRuleVillage(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("judul_dokumen")
    var judulDokumen: String? = null,

    @SerializedName("dokumen")
    var dokumen: String? = null,

    @SerializedName("tanggal_ditetapkan")
    var tanggalDitetapkan: String? = null
)
