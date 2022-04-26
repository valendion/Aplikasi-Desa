package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class ResponseHelpProgramList(
    @SerializedName("data")
    var data: ArrayList<ModelDataHelpProgramList>,

    @SerializedName("status")
    var status: String
)