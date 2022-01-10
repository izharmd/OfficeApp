package com.bws.officeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bws.officeapp.leavestatus.LeaveStatusActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class DashBoardLeaveAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_LEAVE_APP)

        cardViewLeaveRecord.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,LeaveRecordActivity::class.java))
        }

        cardViewApplyLeave.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,LeaveActivity::class.java))
        }

        carViewLeaveStatus.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,LeaveStatusActivity::class.java))
        }

        cardViewProfile.setOnClickListener(){
            startActivity(Intent(this@DashBoardLeaveAppActivity,ProfileActivity::class.java))
        }
    }
}