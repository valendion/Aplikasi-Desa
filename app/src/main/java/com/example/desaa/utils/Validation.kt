package com.example.desaa.utils

object Validation {

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

    fun validationHelpPrograam(value: String): Int{
        return when(value){
            "JAMKESMAS" -> 1
            "BLSM" -> 2
            "BNPT" -> 3
            "PKH" -> 4
            "Bedah Rumah" -> 5
            else -> 0
        }
    }

}