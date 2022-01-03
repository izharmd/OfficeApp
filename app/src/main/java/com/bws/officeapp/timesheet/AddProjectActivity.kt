package com.bws.officeapp.timesheet

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_add_project.*
import kotlinx.android.synthetic.main.activity_daily_time_sheet.*
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.activity_leave.spinner

class AddProjectActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var list_of_project = arrayOf("Select Project Name", "Spense", "Office App")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)
        supportActionBar?.hide()

        spinner1!!.setOnItemSelectedListener(this)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1!!.setAdapter(aa)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}