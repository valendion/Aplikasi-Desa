package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataDecisionHeadman(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("judul_dokumen")
    var judulDokumen: String? = null,

    @SerializedName("dokumen")
    var dokumen: String? = null,

    @SerializedName("nomor_keputusan_kepala_desa")
    var nomorKeputusanKepalaDesa: String? = null,

    @SerializedName("tanggal_keputusan_kepala_desa")
    var tanggalKeputusanKepalaDesa: String? = null
)
