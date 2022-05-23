package com.example.desaa.utils

import android.content.Context
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.progressindicator.CircularProgressIndicator

object ResponseMessage {
    fun alert(message: String, status: StatusResponse, context: Context){
        when(status){
            StatusResponse.SUCCESS ->{
                SweetAlertDialog(
                    context,
                    SweetAlertDialog.SUCCESS_TYPE
                )
                    .setTitleText("Berhasil")
                    .setContentText(message)
                    .show()
            }

            StatusResponse.FAILED ->{
                SweetAlertDialog(
                    context,
                    SweetAlertDialog.WARNING_TYPE
                )
                    .setTitleText("Perhatian !!!")
                    .setContentText(message)
                    .show()
            }
        }
    }

    fun loading(status: Boolean, circularLoading: CircularProgressIndicator){
        when(status){
            true -> circularLoading.visibility = View.VISIBLE
            else -> circularLoading.visibility = View.INVISIBLE
        }
    }
}