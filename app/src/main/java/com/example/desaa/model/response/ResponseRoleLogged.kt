package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRoleLogged(
    @SerializedName("data")
    var data: ModelDataRuleLogged? = null,
)
