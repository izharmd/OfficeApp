package com.bws.officeapp.expense.receipts

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import kotlinx.android.synthetic.main.activity_add_my_receipt.*
import kotlinx.android.synthetic.main.toolba_reminder.*


class AddMyReceiptsActivity:AppCompatActivity() , AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_add_my_receipt)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        ll_receptList.visibility = View.GONE
        ll_addReceipt.visibility = View.VISIBLE


        spMyReceipt!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, R.layout.spinner_item, list_of_month)
        aa.setDropDownViewResource(R.layout.spinner_item)
        spMyReceipt!!.setAdapter(aa)


        recyReceipt.layoutManager = GridLayoutManager(this, 2)

        val data = ArrayList<ReceiptsModel>()

        data.add(ReceiptsModel(R.drawable.receipt1,"13th Jul,2021"))
        data.add(ReceiptsModel(R.drawable.receipt2,"15th July, 2015"))
        data.add(ReceiptsModel(R.drawable.receipt3,"20th July, 2015"))

        val adapter = ReceiptsAdapter(data)
        recyReceipt.adapter = adapter
        adapter.notifyDataSetChanged()

        txtAddNewReceipt.setOnClickListener(){
            ll_receptList.visibility = View.GONE
            ll_addReceipt.visibility = View.VISIBLE

            txtMyReceipt.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
            txtAddNewReceipt.setBackgroundResource(R.drawable.round_button_expense_category_button)
        }

        txtMyReceipt.setOnClickListener(){
            ll_receptList.visibility = View.VISIBLE
            ll_addReceipt.visibility = View.GONE

            txtMyReceipt.setBackgroundResource(R.drawable.round_button_expense_category_button)
            txtAddNewReceipt.setBackgroundResource(R.drawable.round_button_expense_category_button_2)
        }
        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}