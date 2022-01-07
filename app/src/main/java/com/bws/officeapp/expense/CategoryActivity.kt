package com.bws.officeapp.expense

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import kotlinx.android.synthetic.main.activity_ctegory.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class CategoryActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ctegory)
        supportActionBar?.hide()

        ll_mileage.setOnClickListener(){
            startActivity(Intent(this@CategoryActivity,AddNewExpenseActivity::class.java))
        }
        txtViewExpense.setOnClickListener(){
            startActivity(Intent(this@CategoryActivity,ViewExpansegraphActivity::class.java))
        }

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }
}