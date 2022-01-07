package com.bws.officeapp.expense

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.toolbar_expense.*

class ExpenseOverViewActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
        supportActionBar?.hide()

        imvOverView.setOnClickListener(){
            startActivity(Intent(this@ExpenseOverViewActivity,CategoryActivity::class.java))
        }
    }
}