package com.example.desaa.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelDataDetalSocialAssistance(
    val describe: String? = null,
    val helpTarget: String? = null,
    val rangeTime: String? = null,
    val originOfFunds: String? = null,
    val participant: String? = null,
) : Parcelable
