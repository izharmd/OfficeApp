package com.bws.officeapp.expense

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import com.bws.officeapp.expense.expensedetails.ExpenseDetailsActivity
import com.bws.officeapp.expense.utils.MyPopUpMenu
import kotlinx.android.synthetic.main.activity_expanse_graph.*
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.activity_leave.spinner
import kotlinx.android.synthetic.main.toolba_reminder.*

class ViewExpansegraphActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var list_of_month = arrayOf(
        "January",
        "February",
        "March",
        "Appril",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanse_graph)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        spMonth!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMonth!!.setAdapter(aa)

        ll_graph.setOnClickListener(){
            startActivity(Intent(this@ViewExpansegraphActivity,ExpenseDetailsActivity::class.java))
        }

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}