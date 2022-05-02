package com.example.desaa.utils

import android.content.Context

class SharePreferenceApp(context: Context) {

    companion object {

        const val KEY_NAME = "_setting"
        const val KEY_TOKEN = "token"
        const val KEY_ROLE = "role"
        const val KEY_NAME_APARATURE = "nama_aparatur"
        const val KEY_NAME_VILLAGE = "nama_desa"
        const val KEY_NAME_BACKWOOD = "nama_dusun"

        private var INSTANCE: SharePreferenceApp? = null

        fun getInstance(context: Context): SharePreferenceApp {
            if (INSTANCE == null) {
                synchronized(SharePreferenceApp::class) {
                    if (INSTANCE == null) {
                        INSTANCE = SharePreferenceApp(context)
                    }
                }
            }
            return INSTANCE!!
        }
    }


    private val sharedPreferences = context.getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE)
    fun <T> editData(key: String, data: T) {

        val editor = sharedPreferences?.edit()
        when (data) {
            is Boolean -> editor?.putBoolean(key, data)
            is Int -> editor?.putInt(key, data)
            is String -> editor?.putString(key, data)
            else -> throw RuntimeException("$data class or type are not supported")
        }
        editor?.apply()
    }

    fun <T> getData(key: String, default: T): T {
        return when (default) {
            is Boolean -> sharedPreferences?.getBoolean(key, default) as T
            is Int -> sharedPreferences?.getInt(key, default) as T
            is String -> sharedPreferences?.getString(key, default) as T
            else -> throw java.lang.RuntimeException("$default class or type are not supported")
        }
    }

    fun clearDate() {
        sharedPreferences?.edit()?.clear()?.apply()
    }

}