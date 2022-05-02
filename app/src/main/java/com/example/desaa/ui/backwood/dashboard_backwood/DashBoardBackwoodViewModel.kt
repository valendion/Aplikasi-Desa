package com.example.desaa.ui.backwood.dashboard_backwood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ModelDataIntroductionSubmission

class DashBoardBackwoodViewModel: ViewModel() {

   private var _acceptData =  MutableLiveData<ArrayList<ModelDataIntroductionSubmission>>()
   val acceptDatas : LiveData<ArrayList<ModelDataIntroductionSubmission>> get() = _acceptData

   fun addDataAccept(data: ArrayList<ModelDataIntroductionSubmission>){
      _acceptData.postValue(data)
   }

   private var _notAcceptData =  MutableLiveData<ArrayList<ModelDataIntroductionSubmission>>()
   val notAcceptDatas : LiveData<ArrayList<ModelDataIntroductionSubmission>> get() = _notAcceptData

   fun addDataNotAccept(data: ArrayList<ModelDataIntroductionSubmission>){
      _notAcceptData.postValue(data)
   }

}