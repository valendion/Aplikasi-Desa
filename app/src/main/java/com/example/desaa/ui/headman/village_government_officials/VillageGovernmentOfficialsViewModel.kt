package com.example.desaa.ui.headman.village_government_officials

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataVillageGovernmentOfficials
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.SharePreferenceApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VillageGovernmentOfficialsViewModel(private val context: Context): ViewModel(){
    private var sharePreferenceApp = SharePreferenceApp.getInstance(this.context)

    private var _villageGovernment = MutableLiveData<ArrayList<ModelDataVillageGovernmentOfficials>?>()

    val villageGovernment: LiveData<ArrayList<ModelDataVillageGovernmentOfficials>?> get() = _villageGovernment

    fun addVillageGovernment(dataList: ArrayList<ModelDataVillageGovernmentOfficials>?){
        _villageGovernment.postValue(dataList)
    }

    init {
        getDataGovernment()
    }

    private fun getDataGovernment() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataVIllageGovernment =
                NetworkConfig.apiServiceAdminVillage.getVillageGovernmentOfficials(
                    "Bearer ${
                        sharePreferenceApp.getData(
                            SharePreferenceApp.KEY_TOKEN,
                            ""
                        )
                    }"
                )
                addVillageGovernment(dataVIllageGovernment.data)
        }
    }
}