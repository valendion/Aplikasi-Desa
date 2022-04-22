package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseLogout(

    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("message")
    var message: String? = null
)
