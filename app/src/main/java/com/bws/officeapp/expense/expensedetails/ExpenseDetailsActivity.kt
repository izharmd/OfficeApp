package com.bws.officeapp.expense.expensedetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.expense.AddNewExpenseActivity
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_expanse_graph.*
import kotlinx.android.synthetic.main.activity_expense_detsail_list.*
import kotlinx.android.synthetic.main.activity_expense_detsail_list.spMonth
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class ExpenseDetailsActivity:AppCompatActivity() , AdapterView.OnItemSelectedListener {

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
        setContentView(R.layout.activity_expense_detsail_list)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        spMonth!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMonth!!.setAdapter(aa)

        recyExpDetails.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ExpenseDetailsModel>()

        data.add(ExpenseDetailsModel("CF", "Fri Today", "Car Fule", "50.00£"))
        data.add(ExpenseDetailsModel("CF", "Wed 22/07", "Car Fule", "50.00£"))
        data.add(ExpenseDetailsModel("CF", "Fri 17/07", "Car Fule", "75.00£"))
        data.add(ExpenseDetailsModel("CF", "Wed 15/07", "Car Fule", "75.00£"))
        data.add(ExpenseDetailsModel("CF", "Fri 17/07", "Car Fule", "50.00£"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@ExpenseDetailsActivity, R.drawable.line_divider)
        recyExpDetails.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = ExpenseDetailsAdapter(data)
        recyExpDetails.adapter = adapter
        adapter.notifyDataSetChanged()

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}