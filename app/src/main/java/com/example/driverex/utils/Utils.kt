package com.example.driverex.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar


const val KEY = "MYPREF"

    fun Context.showToast(msg : String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

fun Context.sharedPref() : SharedPreferences {
    return getSharedPreferences(KEY,Context.MODE_PRIVATE)
}

fun View.navigation(actionId : Int) {
    Navigation.findNavController(this).navigate(actionId)
}

fun View.snackBar(text : String) {
    Snackbar.make(this, text,Snackbar.LENGTH_SHORT).show()
}


