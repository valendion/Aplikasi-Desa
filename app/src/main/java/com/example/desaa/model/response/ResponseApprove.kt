package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseApprove(
    @SerializedName("success")
    var success: ModelAppprove? = null
)

data class ModelAppprove(
    @SerializedName("message")
    var message: ArrayList<String>? = null
)