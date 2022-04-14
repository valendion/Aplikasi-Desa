package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRuleVillage(

    @SerializedName("data")
    var data: ArrayList<ModelDataRuleVillage>? = null,

    @SerializedName("status")
    var status: String? = null,

)
