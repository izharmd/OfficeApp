package com.bws.timesheet.projectstatus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leavestatus.LeaveStatusAdapter
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusFactory
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusRepo
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusViewModel
import com.bws.officeapp.timesheet.addproject.AddProjectActivity
import com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel.ProjectStatusFactory
import com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel.ProjectStatusRepo
import com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel.ProjectStatusViewModel
import com.bws.officeapp.utils.DateHeader
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_leave_status.*
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.activity_project_status.*
import kotlinx.android.synthetic.main.toolba_reminder.*


class ProjectStatusActvity : AppCompatActivity() {
    lateinit var sharePref: SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_status)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        sharePref = SharedPreference(this)
        DateHeader().dateToHeader(this, textDate, textUserName, "Welcome To Office App")

        recyProjectStatus.layoutManager = LinearLayoutManager(this)
        callProjectStatus()

        txtAdd1.setOnClickListener(){
            startActivity(Intent(this@ProjectStatusActvity, AddProjectActivity::class.java))
        }
    }

    fun callProjectStatus() {

        val pram = Param.PramUserProjectDetails(
            sharePref.getValueString("KEY_USER_ID").toString(),
            "100",
            "1"
        )

        val leaveStatusVM = ViewModelProvider(
            this,
            ProjectStatusFactory(
                ProjectStatusRepo(
                    RetrofitHelper.getInstance().create(ApiInterface::class.java),
                    pram
                ), this
            )
        ).get(ProjectStatusViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)


        leaveStatusVM.liveData.observe(this, Observer {

            when (it) {

                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.NoInternet -> {
                    loadingDialog.dismiss()
                }
                is Response.Success -> {

                    val dividerDrawable =
                        ContextCompat.getDrawable(
                            this@ProjectStatusActvity,
                            R.drawable.line_divider
                        )
                    recyProjectStatus.addItemDecoration(DividerItemDecoration(dividerDrawable))

                    val adapter = ProjectStatusAdapter(it)
                    recyProjectStatus.adapter = adapter
                    adapter.notifyDataSetChanged()

                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this, imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this, imvBack)
    }
}