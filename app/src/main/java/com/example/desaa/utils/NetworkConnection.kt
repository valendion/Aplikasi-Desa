package com.example.desaa.utils

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import com.example.desaa.R

class NetworkConnection(private val context: Context): BroadcastReceiver() {


    override fun onReceive(connect: Context?, intent: Intent?) {
        if (!Common.isConnectedInternet(context)){
            val builder = AlertDialog.Builder(context)
            val layoutDIalog = LayoutInflater.from(context).inflate(R.layout.layout_no_signal, null)
            builder.setView(layoutDIalog)

            val button = layoutDIalog.findViewById<Button>(R.id.btnCheckConnection)

            val alerDialog = builder.create()
            alerDialog.show()
            alerDialog.setCancelable(false)

            alerDialog.window?.setGravity(Gravity.CENTER)

            button.setOnClickListener {
                alerDialog.dismiss()
                onReceive(context, intent)
            }

        }
    }

}