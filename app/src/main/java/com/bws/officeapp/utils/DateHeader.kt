package com.bws.officeapp.utils

import android.app.Activity
import android.content.Context
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class DateHeader {

    fun dateToHeader(context: Activity,txtHeader:TextView,textUserName:TextView,str:String){

        val sdf = SimpleDateFormat("EEE, d MMM yyyy HH:mm")
        val currentDate = sdf.format(Date())
        System.out.println(" C DATE is  "+currentDate)

        txtHeader.text = currentDate.toString()
        textUserName.text = str
    }
}