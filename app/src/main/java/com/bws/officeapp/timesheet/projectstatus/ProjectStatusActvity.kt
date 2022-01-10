package com.bws.timesheet.projectstatus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.AddProjectActivity
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_project_status.*
import kotlinx.android.synthetic.main.toolba_reminder.*


class ProjectStatusActvity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_status)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        recyProjectStatus.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ProjectStatusModel>()

        data.add(ProjectStatusModel("Expense","Shailendra Prashad","Fri 15 Jun 20021","Thu 20 Aug 2021","10:15 AM","Competed"))
        data.add(ProjectStatusModel("iPlus","Shailendra Prashad","Mon 10 Jan 2022","Fri 25 Jan 2022","12:20 PM","In Progress"))
        data.add(ProjectStatusModel("Office App","Shailendra Prashad","Tus 18 Jan 2022","Sat 28 Feb 2022","11:50AM","In Progress"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@ProjectStatusActvity, R.drawable.line_divider)
        recyProjectStatus.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = ProjectStatusAdapter(data)
        recyProjectStatus.adapter = adapter
        adapter.notifyDataSetChanged()

        txtAdd1.setOnClickListener(){
            val intent = Intent(this@ProjectStatusActvity, AddProjectActivity::class.java)
            intent.putExtra("message_key", "Expense")
            intent.putExtra("alocatedBy", "Shailendra Prashad")
            intent.putExtra("date", "Fri 15 Jun 20021")
            intent.putExtra("aggreedDate", "Mon 12 Aug 20021")
            intent.putExtra("time", "10:15 AM")
            startActivity(intent)

            startActivity(Intent(this@ProjectStatusActvity, AddProjectActivity::class.java))
        }
    }
}