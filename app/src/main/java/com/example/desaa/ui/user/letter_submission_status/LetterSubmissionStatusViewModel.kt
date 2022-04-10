package com.example.desaa.ui.user.letter_submission_status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.DummyData

class LetterSubmissionStatusViewModel: ViewModel(){
    private var _listVillageIntroductionCertificate  = MutableLiveData<ArrayList<DummyData>>()
    val listAssistanceBnpt: LiveData<ArrayList<DummyData>> get() = _listVillageIntroductionCertificate

    fun addDatalistVillageIntroductionCertificate(data: ArrayList<DummyData>){
        _listVillageIntroductionCertificate.value = data
    }
}