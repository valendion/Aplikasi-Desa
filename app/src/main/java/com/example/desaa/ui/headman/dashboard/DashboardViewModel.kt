package com.example.desaa.ui.headman.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataInfoVillage

class DashboardViewModel: ViewModel() {
    private var _statistic = MutableLiveData<ModelDataInfoVillage>()

    val statistic: LiveData<ModelDataInfoVillage> get() = _statistic

    fun addValueStatistic(dataStatistic: ModelDataInfoVillage){
        _statistic.postValue(dataStatistic)
    }
}