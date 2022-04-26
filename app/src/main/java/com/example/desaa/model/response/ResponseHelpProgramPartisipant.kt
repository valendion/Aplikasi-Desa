package com.example.desaa.model.response

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class ResponseHelpProgramPartisipant(
    @SerializedName("data")
    var data: ArrayList<ModelDataHelpProgramParticipant>,
)