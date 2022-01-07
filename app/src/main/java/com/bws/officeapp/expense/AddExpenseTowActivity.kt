package com.bws.officeapp.expense

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import kotlinx.android.synthetic.main.activity_add_two_expense.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class AddExpenseTowActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_two_expense)
        supportActionBar?.hide()

        txtViewExpense.setOnClickListener(){
            startActivity(Intent(this@AddExpenseTowActivity,ViewExpansegraphActivity::class.java))
        }
        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }
}