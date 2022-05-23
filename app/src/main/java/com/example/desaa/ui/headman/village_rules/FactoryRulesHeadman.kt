package com.example.desaa.ui.headman.village_rules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryRulesHeadman(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VillageRulesViewModel::class.java)){
            VillageRulesViewModel(this.context) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}