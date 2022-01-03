package com.bws.officeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LeaveRecordActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_record)
        supportActionBar?.hide()
    }
}