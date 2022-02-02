package com.bws.officeapp.timesheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.dailitimesheet.DailyTimeSheetActivity
import com.bws.officeapp.timesheet.searchproject.SearchProjectActivity
import com.bws.timesheet.projectstatus.ProjectStatusActvity
import kotlinx.android.synthetic.main.activity_timesheet.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class TimeSheetDashboardActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        cardViewDailyTimeSheet.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity, DailyTimeSheetActivity::class.java))
        }

        carViewManageProject.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity, SearchProjectActivity::class.java))
        }

        cardViewCalendar.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,CalendarActivity::class.java))
        }

        cardViewProjectStatus.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,ProjectStatusActvity::class.java))
        }

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
}