package com.example.desaa.ui.headman.village_head_decision

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataDecisionHeadman
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VIllageHeadDecisionViewModel(private val context: Context) : ViewModel() {
    private var sharePreferenceApp = SharePreferenceApp.getInstance(this.context)

    private var _villageHeadman = MutableLiveData<ArrayList<ModelDataDecisionHeadman>?>()

    val villageHeadman: LiveData<ArrayList<ModelDataDecisionHeadman>?> get() = _villageHeadman

    fun addVillageHeadman(dataList: ArrayList<ModelDataDecisionHeadman>?) {
        _villageHeadman.postValue(dataList)
    }

    init {
        getDataHeadDecition()
    }

    private fun getDataHeadDecition() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataVillageHeadman = NetworkConfig.apiServiceAdminVillage.getDecisionHeadman(
                "Bearer ${
                    sharePreferenceApp.getData(
                        SharePreferenceApp.KEY_TOKEN,
                        ""
                    )
                }"
            )

            addVillageHeadman(dataVillageHeadman.data)

        }
    }
}