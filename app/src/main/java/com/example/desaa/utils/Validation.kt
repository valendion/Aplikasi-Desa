package com.example.desaa.utils

import kotlinx.coroutines.CoroutineExceptionHandler

object Validation {

    private var message: String = ""

    private val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun formatEmail(input: String): Boolean {

        return input.matches(emailAddressRegex)
    }

    fun validationHelpPrograam(value: String): Int {
        return when (value) {
            "JAMKESMAS" -> 1
            "BLSM" -> 2
            "BNPT" -> 3
            "PKH" -> 4
            "Bedah Rumah" -> 5
            else -> 0
        }
    }

    fun validationBackwood(value: String): Int {
        return when {
            value.contains("Bombongi") -> 1
            value.contains("Tinggito") -> 2
            value.contains("Makkaraeng") -> 3
            value.contains("Padaelo") -> 4
            value.contains("Bugis") -> 5
            else -> 0
        }
    }

    fun exeptionHandler(): CoroutineExceptionHandler {
       return CoroutineExceptionHandler { _, t ->
            {
                t.printStackTrace()
            }
        }
    }

}