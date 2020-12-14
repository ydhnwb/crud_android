package com.ydhnwb.myapplication.utils

object Utils {
    fun isValidEmail(email : String) : Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}