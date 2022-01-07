package com.bws.officeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import com.bws.officeapp.expense.ExpenseOverViewActivity
import com.bws.officeapp.timesheet.TimeSheetDashboardActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

       /* btnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }*/

        btnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ExpenseOverViewActivity::class.java))
        }
    }
}