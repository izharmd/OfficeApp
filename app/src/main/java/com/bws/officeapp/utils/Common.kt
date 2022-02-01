package com.bws.officeapp.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class Common {
    private var _year = 0
    private var _month = 0
    private var _day = 0
    private lateinit var calendar: Calendar
    fun dateDialog(activity: Activity,txt: TextView){
        calendar = Calendar.getInstance()
        _year = calendar.get(Calendar.YEAR)
        _month = calendar.get(Calendar.MONTH)
        _day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(activity, { _, year, month, day_of_month ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day_of_month
            val myFormat = "dd-MM-yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

            txt.text = sdf.format(calendar.time)
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
        //dialog.datePicker.minDate = calendar.timeInMillis
       // calendar.add(Calendar.YEAR, 0)
       // dialog.datePicker.maxDate = calendar.timeInMillis
        dialog.show()
    }
}