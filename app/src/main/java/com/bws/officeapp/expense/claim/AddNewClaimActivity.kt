package com.bws.officeapp.expense.claim

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_add_claim.*
import kotlinx.android.synthetic.main.activity_expense_detsail_list.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class AddNewClaimActivity:AppCompatActivity(), AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_add_claim)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        ll_myClaimList.visibility = View.GONE
        ll_addNewClaim.visibility = View.VISIBLE


        spMonthMyClaim!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMonthMyClaim!!.setAdapter(aa)
        

        recyMyClaim.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<MyClaimModel>()

        data.add(MyClaimModel("AL","Fri Today","Mileage","Pending","25.00£"))
        data.add(MyClaimModel("AL","Wed 22/07","Mileage","Pending","50.00£"))
        data.add(MyClaimModel("AL","Fri 17/12","Train","Pending","10.00£"))
        data.add(MyClaimModel("AL","Wed 15/07","Car Fuel","Approved","25.00£"))
        data.add(MyClaimModel("AL","Wed 15/07","Car Fuel","Approved","25.00£"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@AddNewClaimActivity, R.drawable.line_divider)
        recyMyClaim.addItemDecoration(DividerItemDecoration(dividerDrawable))


        val adapter = MyClaimAdapter(data)
        recyMyClaim.adapter = adapter
        adapter.notifyDataSetChanged()

        txtAddNewClaim.setOnClickListener(){
            ll_myClaimList.visibility = View.GONE
            ll_addNewClaim.visibility = View.VISIBLE

            txtMyClaim.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
            txtAddNewClaim.setBackgroundResource(R.drawable.round_button_expense_category_button)
        }

        txtMyClaim.setOnClickListener(){
            ll_myClaimList.visibility = View.VISIBLE
            ll_addNewClaim.visibility = View.GONE

            txtMyClaim.setBackgroundResource(R.drawable.round_button_expense_category_button)
            txtAddNewClaim.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
        }

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
       
    }
}