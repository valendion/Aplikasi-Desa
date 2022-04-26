package com.example.desaa.ui.headman.village_rules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataRuleVillage

class VillageRulesViewModel: ViewModel() {
    private var _villageRule = MutableLiveData<ArrayList<ModelDataRuleVillage>?>()

     val villageRule: LiveData<ArrayList<ModelDataRuleVillage>?> get() = _villageRule

    fun  addVillageRule(dataList: ArrayList<ModelDataRuleVillage>?){
        _villageRule.postValue(dataList)
    }
}