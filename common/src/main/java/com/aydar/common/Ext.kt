package com.aydar.common

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showLoadingErrorDialog(){
    AlertDialog.Builder(activity as AppCompatActivity)
        .setTitle(getString(R.string.error_while_loading_occurred))
        .setMessage(getString(R.string.try_later))
        .setCancelable(true)
        .setNeutralButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}