package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseTypeLetter(
    @SerializedName("data")
    var data: ArrayList<ModelDataTypeLetter>? = null,
)
