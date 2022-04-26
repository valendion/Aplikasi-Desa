package com.example.desaa.model.response


import com.google.gson.annotations.SerializedName


data class ModelDataHelpProgramList(
    @SerializedName("asal_dana")
    var asalDana: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("keterangan")
    var keterangan: String?,
    @SerializedName("nama_program")
    var namaProgram: String?,
    @SerializedName("rentang_waktu_mulai")
    var rentangWaktuMulai: String?,
    @SerializedName("rentang_waktu_selesai")
    var rentangWaktuSelesai: String?,
    @SerializedName("sasaran_program")
    var sasaranProgram: String?,
    @SerializedName("status")
    var status: String?
)