package com.example.desaa.ui.headman

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desaa.utils.SharePreferenceApp

class HomeHeadmanViewModelFactory(private val application: Application, private val sharePreferenceApp: SharePreferenceApp):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelHeadman::class.java)){
            return ViewModelHeadman(application, sharePreferenceApp) as T
        }
        throw IllegalAccessException("ViewModel class not found")
    }
}