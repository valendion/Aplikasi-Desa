package com.example.desaa.model.response


import com.google.gson.annotations.SerializedName

data class ModelDataTypeLetter(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("nama_jenis_surat")
    var namaJenisSurat: String? = null
)