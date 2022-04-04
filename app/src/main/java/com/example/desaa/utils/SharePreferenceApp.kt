package com.example.desaa.utils

import android.content.Context

class SharePreferenceApp {

    private var _context: Context? = null
    private var INSTANCE: SharePreferenceApp? = null

    companion object{

        const val KEY_NAME = "_setting"
        const val KEY_TOKEN = "token"
        const val KEY_ROLE = "role"
    }

    fun getInstance(): SharePreferenceApp{
        if (INSTANCE == null){
            synchronized(SharePreferenceApp::class){
                if (INSTANCE == null){
                    INSTANCE = SharePreferenceApp()
                }
            }
        }
        return INSTANCE!!
    }

    fun setContext(context: Context){
        _context = context
    }

    private val sharedPreferences = _context?.getSharedPreferences(KEY_NAME, Context.MODE_PRIVATE)
    fun <T> editData(key: String, data: T){

        val editor = sharedPreferences?.edit()
        when(data){
            is Boolean -> editor?.putBoolean(key, data)
            is Int -> editor?.putInt(key, data)
            is String -> editor?.putString(key, data)
            else -> throw RuntimeException("$data class or type are not supported")
        }
        editor?.apply()
    }

    fun <T> getData(key: String, value: T): T{
        return when(value){
            is Boolean -> sharedPreferences?.getBoolean(key, value) as T
            is Int -> sharedPreferences?.getInt(key, value) as T
            is String -> sharedPreferences?.getString(key, value) as T
            else -> throw java.lang.RuntimeException("$value class or type are not supported")
        }
    }

    fun clearDate(){
        sharedPreferences?.edit()?.clear()?.apply()
    }

}