package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataAparatureLogged(

    @SerializedName("nama_jabatan")
    var namaJabatan: String? = null,

    @SerializedName("nama_desa")
    var namaDesa: String? = null,

    @SerializedName("nama_dusun")
    var namaDusun: String? = null,

    @SerializedName("nama_aparatur")
    var namaAparatur: String? = null

)
