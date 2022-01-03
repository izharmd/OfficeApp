package com.bws.officeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bws.officeapp.leavestatus.LeaveStatusActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        cardViewLeaveRecord.setOnClickListener(){
            startActivity(Intent(this@MainActivity,LeaveRecordActivity::class.java))
        }

        cardViewApplyLeave.setOnClickListener(){
            startActivity(Intent(this@MainActivity,LeaveActivity::class.java))
        }

        carViewLeaveStatus.setOnClickListener(){
            startActivity(Intent(this@MainActivity,LeaveStatusActivity::class.java))
        }

        cardViewProfile.setOnClickListener(){
            startActivity(Intent(this@MainActivity,ProfileActivity::class.java))
        }
    }
}