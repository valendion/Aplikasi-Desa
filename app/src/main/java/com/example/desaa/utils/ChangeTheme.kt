package com.example.desaa.utils

import android.app.Activity
import android.content.res.Configuration

class ChangeTheme(activity: Activity) {

    var isDarkTheme = isDarkTheme(activity)

    fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}