package com.example.desaa.ui.headman

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desaa.utils.SharePreferenceApp

class ViewModelHeadman(application: Application, private val sharePreferenceApp: SharePreferenceApp): AndroidViewModel(application) {
    private var _nameHeadman =  MutableLiveData<String>()

    val nameHeadman: LiveData<String> get() = _nameHeadman

    fun addValueHeadman(){
        val name = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_HEADMAN, "")
        _nameHeadman.postValue(name)
    }



    private var _nameVillage =  MutableLiveData<String>()

    val nameVillage: LiveData<String> get() = _nameVillage

    fun addValueVillage(){
        val name = sharePreferenceApp.getData(SharePreferenceApp.KEY_NAME_VILLAGE, "")
        _nameVillage.postValue(name)
    }

}