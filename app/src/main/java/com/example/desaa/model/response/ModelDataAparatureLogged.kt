package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataAparatureLogged(

    @SerializedName("nama_jabatan")
    var namaJabatan: String? = null,

    @SerializedName("nama_desa")
    var namaDesa: String? = null,

    @SerializedName("nama_kepala_desa")
    var namaKepalaDesa: String? = null

)
