package com.example.desaa.ui.headman.village_head_decision

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryDecisionHeadman(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VIllageHeadDecisionViewModel::class.java)){
            VIllageHeadDecisionViewModel(this.context) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}