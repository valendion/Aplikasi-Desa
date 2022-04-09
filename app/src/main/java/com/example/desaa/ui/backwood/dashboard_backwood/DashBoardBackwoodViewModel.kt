package com.example.desaa.ui.backwood.dashboard_backwood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desaa.utils.SharePreferenceApp

class DashBoardBackwoodViewModel(application: Application,private val sharePreferenceApp: SharePreferenceApp): AndroidViewModel(application) {

   private var _role =  MutableLiveData<String>()
   val role : LiveData<String> get() = _role

   fun changevalue(){
      val name = sharePreferenceApp.getData(SharePreferenceApp.KEY_ROLE, "")
      _role.postValue(name)
   }

}