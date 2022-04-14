package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseVillageStatsInfo(
    @SerializedName("data")
    var data: ModelDataInfoVillage,

    @SerializedName("status")
    var status: String
)