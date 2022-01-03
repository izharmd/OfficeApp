package com.bws.officeapp.timesheet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_timesheet.*

class TimeSheetDashboardActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)
        supportActionBar?.hide()

        cardViewDailyTimeSheet.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,DailyTimeSheetActivity::class.java))
        }

        carViewManageProject.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,SearchProjectActivity::class.java))
        }

        cardViewCalendar.setOnClickListener(){
            startActivity(Intent(this@TimeSheetDashboardActivity,CalendarActivity::class.java))
        }
    }
}