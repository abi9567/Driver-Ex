package com.example.driverex.extention

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.driverex.constants.KEY


fun Context.showToast(msg : String) {
    Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}

fun Context.sharedPref() : SharedPreferences {
    return getSharedPreferences(KEY, Context.MODE_PRIVATE)
}