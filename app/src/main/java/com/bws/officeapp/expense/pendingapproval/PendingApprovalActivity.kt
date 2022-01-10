package com.bws.officeapp.expense.pendingapproval

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.expense.expensedetails.ExpenseDetailsAdapter
import com.bws.officeapp.expense.expensedetails.ExpenseDetailsModel
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_pending_approval.*
import kotlinx.android.synthetic.main.toolba_reminder.*


class PendingApprovalActivity:AppCompatActivity() , AdapterView.OnItemSelectedListener {


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
        setContentView(R.layout.activity_pending_approval)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        spMonth!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMonth!!.setAdapter(aa)

        recyPendignApproval.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<PendingApprovalModel>()

        data.add(PendingApprovalModel("M", "Fri Today", "Car Fule", "50.00£","Pending"))
        data.add(PendingApprovalModel("T", "Wed 22/07", "Car Fule", "50.00£","Pending"))
        data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "75.00£","Pending"))
        data.add(PendingApprovalModel("T", "Wed 15/07", "Car Fule", "75.00£","Pending"))
        data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "50.00£","Pending"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@PendingApprovalActivity, R.drawable.line_divider)
        recyPendignApproval.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = PendingApprovalAdapter(data)
        recyPendignApproval.adapter = adapter
        adapter.notifyDataSetChanged()

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)

        txtViewExpense.setOnClickListener(){

            txtPendignApproval.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
            txtViewExpense.setBackgroundResource(R.drawable.round_button_expense_category_button)
            val data = ArrayList<PendingApprovalModel>()

            data.add(PendingApprovalModel("M", "Fri Today", "Car Fule", "50.00£","Approved"))
            data.add(PendingApprovalModel("T", "Wed 22/07", "Car Fule", "50.00£","Approved"))
            data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "75.00£","Approved"))
           // data.add(PendingApprovalModel("T", "Wed 15/07", "Car Fule", "75.00£","Pending.."))
           // data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "50.00£","Pending.."))

            val adapter = PendingApprovalAdapter(data)
            recyPendignApproval.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        txtPendignApproval.setOnClickListener(){

            txtPendignApproval.setBackgroundResource(R.drawable.round_button_expense_category_button)
            txtViewExpense.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
            val data = ArrayList<PendingApprovalModel>()
            data.add(PendingApprovalModel("M", "Fri Today", "Car Fule", "50.00£","Pending.."))
            data.add(PendingApprovalModel("T", "Wed 22/07", "Car Fule", "50.00£","Pending.."))
            data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "75.00£","Pending.."))
            data.add(PendingApprovalModel("T", "Wed 15/07", "Car Fule", "75.00£","Pending.."))
            data.add(PendingApprovalModel("M", "Fri 17/07", "Car Fule", "50.00£","Pending.."))

            val dividerDrawable =
                ContextCompat.getDrawable(this@PendingApprovalActivity, R.drawable.line_divider)
            recyPendignApproval.addItemDecoration(DividerItemDecoration(dividerDrawable))

            val adapter = PendingApprovalAdapter(data)
            recyPendignApproval.adapter = adapter
            adapter.notifyDataSetChanged()

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}