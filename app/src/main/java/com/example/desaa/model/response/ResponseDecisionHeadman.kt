package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseDecisionHeadman(

    @SerializedName("data")
    var data: ArrayList<ModelDataDecisionHeadman>? = null,

    @SerializedName("status")
    var status: String? = null,

)
