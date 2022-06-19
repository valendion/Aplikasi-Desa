package com.example.desaa.ui.user.letter_submission_status

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataIntroductionVillageLetter
import com.example.desaa.utils.NetworkConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LetterSubmissionStatusViewModel: ViewModel() {

    private val dataBombongi = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataTinggitto = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataMakkaraeng = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataPadaelo = arrayListOf<ModelDataIntroductionVillageLetter>()
    private val dataBugis = arrayListOf<ModelDataIntroductionVillageLetter>()


    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private fun setLoading(status: Boolean) {
        _isLoading.postValue(status)
    }

    private var _listVillageBombongiIntroductionCertificate =
        MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceBombongi: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageBombongiIntroductionCertificate

    fun addDatalistVillageBombongiIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listVillageBombongiIntroductionCertificate.postValue(data)
    }

    private var _listVillageTinggittoiIntroductionCertificate =
        MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceTinggitto: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageTinggittoiIntroductionCertificate

    fun addDatalistVillageTinggittoIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listVillageTinggittoiIntroductionCertificate.postValue(data)
    }

    private var _listVillagePadaeloIntroductionCertificate =
        MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistancePadaelo: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillagePadaeloIntroductionCertificate

    fun addDatalistVillagePadaeloIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listVillagePadaeloIntroductionCertificate.postValue(data)
    }

    private var _listVillageMakkaraengIntroductionCertificate =
        MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceMakkaraeng: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageMakkaraengIntroductionCertificate

    fun addDatalistVillageMakkaraengIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listVillageMakkaraengIntroductionCertificate.postValue(data)
    }

    private var _listVillageBugisIntroductionCertificate =
        MutableLiveData<ArrayList<ModelDataIntroductionVillageLetter>>()
    val listAssistanceBugis: LiveData<ArrayList<ModelDataIntroductionVillageLetter>> get() = _listVillageBugisIntroductionCertificate

    fun addDatalistVillageBugisIntroductionCertificate(data: ArrayList<ModelDataIntroductionVillageLetter>) {
        _listVillageBugisIntroductionCertificate.postValue(data)
    }

    fun getDataSubmissionStatus() {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                setLoading(true)

                val dataVillage =
                    NetworkConfig.apiServiceAdminVillage.getIntroductionVillageLetterList()

                dataVillage.data.forEach { data ->
                    if (data.dusunId == 1) {
                        dataBombongi.add(data)
                    }
                    if (data.dusunId == 2) {
                        dataTinggitto.add(data)
                    }
                    if (data.dusunId == 3) {
                        dataMakkaraeng.add(data)
                    }
                    if (data.dusunId == 4) {
                        dataPadaelo.add(data)
                    }
                    if (data.dusunId == 5) {
                        dataBugis.add(data)
                    }
                }

                addDatalistVillageBombongiIntroductionCertificate(dataBombongi)

                addDatalistVillageTinggittoIntroductionCertificate(dataTinggitto)

                addDatalistVillagePadaeloIntroductionCertificate(dataPadaelo)

                addDatalistVillageMakkaraengIntroductionCertificate(dataMakkaraeng)

                addDatalistVillageBugisIntroductionCertificate(dataBugis)

                setLoading(false)

            }catch (e: Exception){
                Log.e("Submission Letter User", e.message.toString())
            }
        }
    }
}