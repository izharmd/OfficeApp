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

class LeaveActivity:AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var list_of_items = arrayOf("Select", "Earned Leave", "Casual Leave","Sick Leave")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave)
        supportActionBar?.hide()

        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)


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

                    Log.d("qwe","qewrt")

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