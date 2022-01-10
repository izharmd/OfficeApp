package com.bws.officeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolba_reminder.*

class LeaveRecordActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_record)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_LEAVE_APP)
    }
}