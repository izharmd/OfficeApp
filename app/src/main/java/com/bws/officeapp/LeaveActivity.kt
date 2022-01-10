package com.bws.officeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class LeaveActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var list_of_items = arrayOf("Select", "Earned Leave", "Casual Leave", "Sick Leave")
    var list_FromDate = arrayOf("Full Day","Half Day")
    var list_ToDate = arrayOf("Full Day","Half Day")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_LEAVE_APP)

        spinner!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)


        spFromDate!!.setOnItemSelectedListener(this)
        val adapterFromDate =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, list_FromDate)
        adapterFromDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFromDate!!.setAdapter(adapterFromDate)

        spToDate!!.setOnItemSelectedListener(this)
        val adapterToDate = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_ToDate)
        adapterToDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spToDate!!.setAdapter(adapterToDate)


        imv_Shutdown.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, imv_Shutdown)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.setting ->
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileActivity::class.java
                            )
                        )
                    R.id.logOut ->
                        // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe", "qewrt")

                }
                true
            })
            popupMenu.show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}