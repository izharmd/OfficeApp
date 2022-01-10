package com.bws.officeapp.timesheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_daily_time_sheet.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class DailyTimeSheetActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var list_of_project = arrayOf("Select", "Office Project", "i+Plus", "AEML")
    var list_of_project_status = arrayOf("Select", "Completed", "Not Completed", "On Going")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_time_sheet)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProject!!.setAdapter(aa)

        val status =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_project_status)
        status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProjectStatus!!.setAdapter(status)

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