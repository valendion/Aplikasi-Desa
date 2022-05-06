package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseStatus(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,
)
