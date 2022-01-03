package com.bws.officeapp.leavestatus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_leave_status.*
import java.util.ArrayList

class LeaveStatusActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_status)
        supportActionBar?.hide()

        recyLeaveStatus.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<LeaveStatusModel>()

        data.add(LeaveStatusModel("00120","Mr. Izhar","2 Day's","7 Dec 2021","8 Dec 2021","Pending","We can't approve now"))
        data.add(LeaveStatusModel("00432","Mr. Dave","1 Day's","3 Jan 2022","4 Jan 2022","Pending","We can't approve now"))
        data.add(LeaveStatusModel("00120","Mr. Miles","4 Day's","4 Feb 2022","6 Dec 2022","Approved","Your leave is approved"))
        data.add(LeaveStatusModel("00120","Mr. Milton","2 Day's","7 Dec 2021","8 Dec 2021","Approved","Your leave is approved"))


        val dividerDrawable =
            ContextCompat.getDrawable(this@LeaveStatusActivity, R.drawable.line_divider)
        recyLeaveStatus.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = LeaveStatusAdapter(data)
        recyLeaveStatus.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}