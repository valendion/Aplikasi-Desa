package com.example.desaa.ui.headman.village_rules

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataRuleVillage
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VillageRulesViewModel(private val context: Context) : ViewModel() {
    private var sharePreferenceApp = SharePreferenceApp.getInstance(this.context)

    private var _villageRule = MutableLiveData<ArrayList<ModelDataRuleVillage>?>()

    val villageRule: LiveData<ArrayList<ModelDataRuleVillage>?> get() = _villageRule

    fun addVillageRule(dataList: ArrayList<ModelDataRuleVillage>?) {
        _villageRule.postValue(dataList)
    }

    init {
        getDataRules()
    }

    private fun getDataRules() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataVillageRule = NetworkConfig.apiServiceAdminVillage.getRuleVillage(
                "Bearer ${
                    sharePreferenceApp.getData(
                        SharePreferenceApp.KEY_TOKEN,
                        ""
                    )
                }"
            )
            addVillageRule(dataVillageRule.data)
        }
    }
}