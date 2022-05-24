package com.example.desaa.ui.user.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataInfoVillage
import com.example.desaa.utils.NetworkConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticViewModel() : ViewModel() {
//    private var sharePreferenceApp = SharePreferenceApp.getInstance(this.context)

    private var _statistic = MutableLiveData<ModelDataInfoVillage>()

    val statistic: LiveData<ModelDataInfoVillage> get() = _statistic

    fun addValueStatistic(dataStatistic: ModelDataInfoVillage) {
        _statistic.postValue(dataStatistic)
    }

    init {
        getStatistic()
    }

    private fun getStatistic() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataStatistic = NetworkConfig.apiServiceAdminVillage.getStatisticVillage()

            addValueStatistic(dataStatistic.data)
        }
    }

}