package com.example.desaa.ui.backwood.detail_backwood

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailBackwoodViewModel: ViewModel() {
    private val _dataDetailBackwood:  MutableLiveData<MutableList<String?>>? by lazy {
        MutableLiveData<MutableList<String?>>()
    }

    fun getDataDetailBackwood(): MutableLiveData<MutableList<String?>>? {return _dataDetailBackwood}

    fun setDataDetailBackwood(input: MutableList<String?>){
        _dataDetailBackwood?.setValue(input)
    }

}