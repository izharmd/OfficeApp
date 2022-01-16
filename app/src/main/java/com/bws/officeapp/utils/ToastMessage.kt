package com.bws.officeapp.utils

import android.content.Context
import android.widget.Toast

class ToastMessage {
    companion object{
        fun message(context: Context,message: String){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show().toString()
        }
    }
}
