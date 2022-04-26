package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName


data class ModelDataHelpProgramParticipant(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("nama_lengkap")
    var namaLengkap: String? = null,
    @SerializedName("nama_program")
    var namaProgram: String? = null,
    @SerializedName("nik")
    var nik: String? = null,
    @SerializedName("program_bantuan_id")
    var programBantuanId: Int? = null,
    @SerializedName("status")
    var status: String? = null
)