package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseAparatureLogged(
    @SerializedName("data")
    var data: ModelDataAparatureLogged,

    @SerializedName("status")
    var status: String
)
