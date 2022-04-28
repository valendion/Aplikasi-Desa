package com.example.desaa.model.response


import com.google.gson.annotations.SerializedName

data class ModelDataIntroductionVillageLetter(
    @SerializedName("dusun_id")
    var dusunId: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("jenis_surat_akan_dibuat")
    var jenisSuratAkanDibuat: String? = null,
    @SerializedName("nama_penduduk")
    var namaPenduduk: String? = null,
    @SerializedName("nik")
    var nik: String? = null,
    @SerializedName("status_acc")
    var statusAcc: String? = null
)