package com.example.desaa.ui.headman.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactoryDashboardHeadman(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            DashboardViewModel(this.context) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}