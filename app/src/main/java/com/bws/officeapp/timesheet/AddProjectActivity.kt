package com.bws.officeapp.timesheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_add_project.*

import kotlinx.android.synthetic.main.toolba_reminder.*

class AddProjectActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var list_of_project = arrayOf("Select Project Name", "Spense", "Office App")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        spinner1!!.setOnItemSelectedListener(this)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1!!.setAdapter(aa)
        spinner1.setSelection(1)

        var projectName = intent.getStringExtra("message_key").toString()
        var alocatedxBy = intent.getStringExtra("alocatedBy").toString()
        val date = intent.getStringExtra("date")
        val agreedDate = intent.getStringExtra("aggreedDate")
        val time = intent.getStringExtra("time")

        txtAlocatedBy.setText(alocatedxBy)
        txtDate.setText(date)
        edtAggredDate.setText(agreedDate)
        edtAggredTime.setText(time)

        imv_Shutdown.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, imv_Shutdown)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.setting ->
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileTimeSheetActivity::class.java
                            )
                        )
                    R.id.logOut ->
                       // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe","qewrt");

                }
                true
            })
            popupMenu.show()
        }

        imvBack.setOnClickListener(){
            finish()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}