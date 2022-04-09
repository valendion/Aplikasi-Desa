package com.example.desaa.ui.backwood.detail_backwood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailBackwoodViewModel: ViewModel() {
    private val _dataDetailBackwood: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    val getDataDetailBackwood: LiveData<String> get() = _dataDetailBackwood

    fun setDataDetailBackwood(input: String){
        _dataDetailBackwood.postValue(input)
    }

}