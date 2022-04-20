package com.example.desaa.ui.headman.village_government_officials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataVillageGovernmentOfficials

class VillageGovernmentOfficialsViewModel(): ViewModel(){
    private var _villageGovernment = MutableLiveData<ArrayList<ModelDataVillageGovernmentOfficials>?>()

    val villageGovernment: LiveData<ArrayList<ModelDataVillageGovernmentOfficials>?> get() = _villageGovernment

    fun addVillageGovernment(dataList: ArrayList<ModelDataVillageGovernmentOfficials>?){
        _villageGovernment.postValue(dataList)
    }
}