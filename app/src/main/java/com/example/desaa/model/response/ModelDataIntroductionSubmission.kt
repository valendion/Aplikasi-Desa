package com.example.desaa.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelDataIntroductionSubmission(
    @SerializedName("foto_ktp")
    var fotoKtp: String?  = null,
    @SerializedName("id")
    var id: Int?  = null,
    @SerializedName("jenis_surat_akan_dibuat")
    var jenisSuratAkanDibuat: String?  = null,
    @SerializedName("keterangan")
    var keterangan: String?  = null,
    @SerializedName("nama_penduduk")
    var namaPenduduk: String?  = null,
    @SerializedName("nik")
    var nik: String?  = null,
    @SerializedName("status_acc")
    var statusAcc: String?  = null
):Parcelable