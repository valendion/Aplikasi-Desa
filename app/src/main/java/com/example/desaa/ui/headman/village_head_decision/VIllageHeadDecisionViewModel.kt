package com.example.desaa.ui.headman.village_head_decision

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desaa.model.response.ModelDataDecisionHeadman

class VIllageHeadDecisionViewModel(application: Application): AndroidViewModel(application) {
    private var _villageHeadman = MutableLiveData<ArrayList<ModelDataDecisionHeadman>?>()

    val villageHeadman: LiveData<ArrayList<ModelDataDecisionHeadman>?> get() = _villageHeadman

    fun addVillageHeadman(dataList: ArrayList<ModelDataDecisionHeadman>?){
        _villageHeadman.postValue(dataList)
    }
}