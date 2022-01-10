package com.bws.officeapp.timesheet.projectlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.AddProjectActivity
import com.bws.officeapp.timesheet.ProfileTimeSheetActivity
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.toolba_reminder.*


class ProjectListActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        recyProjectList.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ProjectListModel>()

        data.add(ProjectListModel("Expense","Shailendra Prasad","01 Dec 2021","02 Jan 2022","3 Day's 4 hr's","3 Day's","Completed"))
        data.add(ProjectListModel("iPlus","Shailendra Prasad","10 Dec 2021","12 Dec 2021","6 Day's 2 hr's","6 Day's","In Peogress"))
        data.add(ProjectListModel("Office App","Shailendra Prasad","03 Dec 2022","06 Jan 2022","3 Day's 4 hr's","3 Day's","Completed"))

        val dividerDrawable =
            ContextCompat.getDrawable(this@ProjectListActivity, R.drawable.line_divider)
        recyProjectList.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = ProjectListAdapter(data)
        recyProjectList.adapter = adapter
        adapter.notifyDataSetChanged()

        txtAdd.setOnClickListener(){
            startActivity(Intent(this@ProjectListActivity,AddProjectActivity::class.java))
        }


        imv_Shutdown.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, imv_Shutdown)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.setting ->
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileTimeSheetActivity::class.java
                            )
                        )
                    R.id.logOut ->
                        // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe","qewrt");

                }
                true
            })
            popupMenu.show()
        }

        imvBack.setOnClickListener(){
            finish()
        }
    }
}