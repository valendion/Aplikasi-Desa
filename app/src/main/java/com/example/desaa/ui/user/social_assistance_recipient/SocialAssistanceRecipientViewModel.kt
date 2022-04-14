package com.example.desaa.ui.user.social_assistance_recipient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.DummyData

class SocialAssistanceRecipientViewModel : ViewModel() {
    // List social assistance BNPT
    private var _listAssistanceBnpt  = MutableLiveData<ArrayList<DummyData>>()
    val listAssistanceBnpt: LiveData<ArrayList<DummyData>> get() = _listAssistanceBnpt

    fun addDatalistAssistanceBnpt(data: ArrayList<DummyData>){
        _listAssistanceBnpt.value = data
    }
    // list social assitance Jamkesmas
    private var _listAssistanceJamkes = MutableLiveData<ArrayList<DummyData>>()
    val listAssistanceJamkes: LiveData<ArrayList<DummyData>> get() = _listAssistanceJamkes

    fun addDatalistAssistanceJamkes(data: ArrayList<DummyData>){
        _listAssistanceJamkes.value = data
    }
}