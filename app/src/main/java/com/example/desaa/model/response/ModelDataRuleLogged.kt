package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ModelDataRuleLogged(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("role")
    var role: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("email")
    var email: String? = null
)