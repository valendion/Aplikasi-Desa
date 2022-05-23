package com.example.desaa.ui.headman.village_government_officials

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryGovermentHeadman(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VillageGovernmentOfficialsViewModel::class.java)){
            VillageGovernmentOfficialsViewModel(this.context) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}