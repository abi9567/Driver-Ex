package com.example.driverex.extention

import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

fun View.navigation(actionId : Int) {
    Navigation.findNavController(this).navigate(actionId)
}

fun View.snackBar(text : String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(ContextCompat.getColor(context,android.R.color.holo_green_dark))
        .show()
}

