package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName

data class ResponseVillageGovernmentOfficials(

    @SerializedName("data")
    var data: ArrayList<ModelDataVillageGovernmentOfficials>? = null,

    @SerializedName("status")
    var status: String? = null,

)
