package com.example.desaa.ui.user.letter_submission_status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.DummyData
import com.example.desaa.model.response.ModelDataIntroductionVillageLetter

class LetterSubmissionStatusViewModel: ViewModel(){
    private var _listVillageBombongiIntroductionCertificate  = MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceBombongi: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageBombongiIntroductionCertificate

    fun addDatalistVillageBombongiIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>){
        _listVillageBombongiIntroductionCertificate.postValue(data)
    }

    private var _listVillageTinggittoiIntroductionCertificate  = MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceTinggitto: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageTinggittoiIntroductionCertificate

    fun addDatalistVillageTinggittoIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>){
        _listVillageTinggittoiIntroductionCertificate.postValue(data)
    }

    private var _listVillagePadaeloIntroductionCertificate  = MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistancePadaelo: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillagePadaeloIntroductionCertificate

    fun addDatalistVillagePadaeloIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>){
        _listVillagePadaeloIntroductionCertificate.postValue(data)
    }

    private var _listVillageMakkaraengIntroductionCertificate  = MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceMakkaraeng: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageMakkaraengIntroductionCertificate

    fun addDatalistVillageMakkaraengIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>){
        _listVillageMakkaraengIntroductionCertificate.postValue(data)
    }

    private var _listVillageBugisIntroductionCertificate  = MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceBugis: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageBugisIntroductionCertificate

    fun addDatalistVillageBugisIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>){
        _listVillageBugisIntroductionCertificate.postValue(data)
    }
}