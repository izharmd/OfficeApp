package com.bws.officeapp.expense.trip

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
import kotlinx.android.synthetic.main.activity_add_new_trip.*
import kotlinx.android.synthetic.main.activity_expense_detsail_list.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class AddNewTripActivity:AppCompatActivity() , AdapterView.OnItemSelectedListener{

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
        setContentView(R.layout.activity_add_new_trip)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        ll_myClaimList.visibility = View.GONE
        ll_addNewClaim.visibility = View.VISIBLE


        spMonthRecentTrip!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMonthRecentTrip!!.setAdapter(aa)


        recyRecentTrip.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<RecentTripModel>()

        data.add(RecentTripModel("AL","Fri Today","Car","London-Reading"))
        data.add(RecentTripModel("AL","Wed 22/07","Train","Reading-Manchester"))
        data.add(RecentTripModel("AL","Fri 17/12","Train","London-Reading"))
        data.add(RecentTripModel("AL","Wed 15/07","Car ","London-Reading"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@AddNewTripActivity, R.drawable.line_divider)
        recyRecentTrip.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = RecentTripAdapter(data)
        recyRecentTrip.adapter = adapter
        adapter.notifyDataSetChanged()

        txtAddNewTrip.setOnClickListener(){
            ll_myClaimList.visibility = View.GONE
            ll_addNewClaim.visibility = View.VISIBLE

            txtRecentTrip.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
            txtAddNewTrip.setBackgroundResource(R.drawable.round_button_expense_category_button)
        }

        txtRecentTrip.setOnClickListener(){
            ll_myClaimList.visibility = View.VISIBLE
            ll_addNewClaim.visibility = View.GONE

            txtRecentTrip.setBackgroundResource(R.drawable.round_button_expense_category_button)
            txtAddNewTrip.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
        }

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}