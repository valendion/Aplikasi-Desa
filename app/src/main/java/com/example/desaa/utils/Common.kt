package com.example.desaa.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class Common {

    companion object {
        fun isConnectedInternet(context: Context): Boolean {
            val connectionManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val info = connectionManager.activeNetwork ?: return false
                val actNetwork = connectionManager.getNetworkCapabilities(info) ?: return false
                return when {
                    actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else  {
                var status = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    connectionManager.registerDefaultNetworkCallback(object :
                        ConnectivityManager.NetworkCallback() {

                        override fun onAvailable(network: Network) {
                            super.onAvailable(network)
                            status = true
                        }

                        override fun onLost(network: Network) {
                            super.onLost(network)
                            status = false
                        }
                    })
                }

                return status
            }
        }
    }
}