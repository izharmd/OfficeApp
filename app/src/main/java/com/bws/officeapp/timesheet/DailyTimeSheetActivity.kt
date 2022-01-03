package com.bws.officeapp.timesheet

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_daily_time_sheet.*
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.activity_leave.spinner

class DailyTimeSheetActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var list_of_project = arrayOf("Select", "Office Project", "i+Plus", "AEML")
    var list_of_project_status = arrayOf("Select", "Completed", "Not Completed", "On Going")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_time_sheet)
        supportActionBar?.hide()

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProject!!.setAdapter(aa)

        val status =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project_status)
        status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProjectStatus!!.setAdapter(status)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}