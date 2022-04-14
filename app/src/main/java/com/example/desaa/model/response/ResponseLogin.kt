package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("type")
    var type: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("token")
    var token: String? = null
)
