package com.example.desaa.ui.user.social_assistance_recipient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataHelpProgramList
import com.example.desaa.model.response.ModelDataHelpProgramParticipant

class SocialAssistanceRecipientViewModel : ViewModel() {

    // Help Program List
    private var _listHelpProgram = MutableLiveData<ArrayList<ModelDataHelpProgramList>>()
    val listHelpProgram: LiveData<ArrayList<ModelDataHelpProgramList>> get() = _listHelpProgram

    fun addDataHelpPrograms(data: ArrayList<ModelDataHelpProgramList>){
        _listHelpProgram.postValue(data)
    }

    // list social assitance
    private var _listAssistance = MutableLiveData<ArrayList<ModelDataHelpProgramParticipant>>()
    val listAssistance: LiveData<ArrayList<ModelDataHelpProgramParticipant>> get() = _listAssistance

    fun addDatalistAssistance(data: ArrayList<ModelDataHelpProgramParticipant>){
        _listAssistance.postValue(data)
    }
}