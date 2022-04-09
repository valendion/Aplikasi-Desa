package com.example.desaa.ui.backwood.dashboard_backwood

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desaa.utils.SharePreferenceApp

class DashBoardViewModelFactory(private val application: Application,private val sharePreferenceApp: SharePreferenceApp):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashBoardBackwoodViewModel::class.java)){
            return DashBoardBackwoodViewModel(application, sharePreferenceApp) as T
        }
        throw IllegalAccessException("ViewModel class not found")
    }
}