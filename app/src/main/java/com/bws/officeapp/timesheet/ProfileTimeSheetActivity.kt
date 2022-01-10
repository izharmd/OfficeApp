package com.bws.officeapp.timesheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.toolba_reminder.*

class ProfileTimeSheetActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_profile_timesheet)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        imvBack.setOnClickListener(){
            finish()
        }
    }
}