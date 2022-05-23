package com.example.desaa.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desaa.model.response.ResponseStatus
import com.example.desaa.utils.NetworkConfig
import com.example.desaa.utils.ResponseMessage
import com.example.desaa.utils.SharePreferenceApp
import com.example.desaa.utils.StatusResponse
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelLogin(private var context: Context, private val loading: CircularProgressIndicator) : ViewModel() {
    private var _email: String = ""
    private var _password: String = ""

    private var sharePreferenceApp = SharePreferenceApp.getInstance(this.context)

    fun getEmailPass(email: String, password: String) {
        _email = email
        _password = password
    }

     fun getToken() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataToken =
                NetworkConfig.apiServiceAdminVillage.getToken(_email, _password)

            if (dataToken.code() == 200) {
                if (dataToken.isSuccessful) {

                    val dataRole = dataToken.body()
                    dataRole?.token?.let {
                        getRole(it)
                        sharePreferenceApp.editData(SharePreferenceApp.KEY_TOKEN, it)
                    }
                }
            } else {
                withContext(Dispatchers.Main){
                    val errorMessage = Gson().fromJson(
                        dataToken.errorBody()?.string(),
                        ResponseStatus::class.java
                    )

                    errorMessage.message?.let {
                        ResponseMessage.alert(it, StatusResponse.FAILED, context)
                        ResponseMessage.loading(false, loading)
                    }

                }
            }
        }
    }

    private  fun getRole(token: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val dataRole = NetworkConfig.apiServiceAdminVillage.getAparatureRole(
                "Bearer $token"
            )
            if (dataRole.code() == 200) {
                if (dataRole.isSuccessful) {
                    val data = dataRole.body()
                    data?.data?.role?.let {
                        getProfile(it)
                        sharePreferenceApp.editData(SharePreferenceApp.KEY_ROLE, it)
                    }

                }
            } else {
                val errorMessage = Gson().fromJson(
                    dataRole.errorBody()?.string(),
                    ResponseStatus::class.java
                )
                errorMessage.message?.let { ResponseMessage.alert(it, StatusResponse.FAILED, context)
                    ResponseMessage.loading(false, loading)}
            }

        }
    }

    fun getProfile(role: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val dataProfile = NetworkConfig.apiServiceAdminVillage.getAparatureogged(
                "Bearer ${
                    sharePreferenceApp.getData(
                        SharePreferenceApp.KEY_TOKEN, ""
                    )
                }"
            )

            if (dataProfile.code() == 200) {
                if (dataProfile.isSuccessful) {
                    val data = dataProfile.body()

                    data?.data?.apply {

                        if (role == "kepala_desa") {
                            sharePreferenceApp.editData(
                                SharePreferenceApp.KEY_NAME_APARATURE,
                                namaAparatur
                            )
                            sharePreferenceApp.editData(SharePreferenceApp.KEY_NAME_VILLAGE, namaDesa)
                        } else {
                            sharePreferenceApp.editData(
                                SharePreferenceApp.KEY_NAME_APARATURE,
                                namaAparatur
                            )
                            sharePreferenceApp.editData(SharePreferenceApp.KEY_NAME_VILLAGE, namaDesa)
                            sharePreferenceApp.editData(SharePreferenceApp.KEY_NAME_BACKWOOD, namaDusun)
                        }
                    }
                }
            } else {
                val errorMessage = Gson().fromJson(
                    dataProfile.errorBody()?.string(),
                    ResponseStatus::class.java
                )
                errorMessage.message?.let { ResponseMessage.alert(it, StatusResponse.FAILED, context)
                    ResponseMessage.loading(false, loading)}
            }
        }
    }
}