package com.bws.officeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.expense.ExpenseOverViewActivity
import com.bws.officeapp.policy.PolicyDocumentActivity
import com.bws.officeapp.timesheet.TimeSheetDashboardActivity
import kotlinx.android.synthetic.main.activity_dashboard_office.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class DashboardOfficeAppActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_office)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_OFFICE_APP)

        cardViewExpenseApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,ExpenseOverViewActivity::class.java))
        }

        cardViewLeaveApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,DashBoardLeaveAppActivity::class.java))
        }

        carViewTimeSheetApp.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,TimeSheetDashboardActivity::class.java))
        }

        cardViewProfile.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,ProfileActivity::class.java))
        }

        cardViewPolicyDocument.setOnClickListener(){
            startActivity(Intent(this@DashboardOfficeAppActivity,PolicyDocumentActivity::class.java))
        }
    }
}