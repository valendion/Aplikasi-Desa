package com.example.desaa.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.progressindicator.CircularProgressIndicator

class FactoryLogin(private val context: Context, private val loading: CircularProgressIndicator): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelLogin::class.java)){
            ViewModelLogin(this.context, loading) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}