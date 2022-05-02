package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("error")
    var success: ModelError? = null
)

data class ModelError(
    @SerializedName("message")
    var message: List<String>? = null
)