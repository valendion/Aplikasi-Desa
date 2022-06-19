package com.example.desaa.ui.user.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataInfoVillage
import com.example.desaa.utils.NetworkConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticViewModel() : ViewModel() {


    private var _statistic = MutableLiveData<ModelDataInfoVillage>()
    val statistic: LiveData<ModelDataInfoVillage> get() = _statistic
    fun addValueStatistic(dataStatistic: ModelDataInfoVillage) {
        _statistic.postValue(dataStatistic)
    }

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private fun setLoading(status: Boolean){
        _isLoading.postValue(status)
    }

    fun getStatistic() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                setLoading(true)

                val dataStatistic = NetworkConfig.apiServiceAdminVillage.getStatisticVillage()
                addValueStatistic(dataStatistic.data)

                setLoading(false)
            }catch (e: Exception){
                setLoading(false)
                Log.e("Statistic User", e.message.toString())
            }
        }
    }

}