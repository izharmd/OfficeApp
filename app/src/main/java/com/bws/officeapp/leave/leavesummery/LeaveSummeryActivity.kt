package com.bws.officeapp.leave.leavesummery

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityLeaveBinding
import com.bws.officeapp.databinding.ActivityLeaveRecordBinding
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveFactory
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveRepo
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyViewModel
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference
import com.bws.officeapp.utils.ToastMessage
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.util.*

class LeaveSummeryActivity : AppCompatActivity() {

    lateinit var binding: ActivityLeaveRecordBinding

    lateinit var sharePref:SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_leave_record)
       // setContentView(R.layout.activity_leave_record)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_LEAVE_APP)

        sharePref = SharedPreference(this)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val pram = Param.PramUserLeaveSummary(sharePref.getValueString("KEY_USER_ID").toString(), currentYear.toString())


        val applyVM = ViewModelProvider(
            this,
            LeaveSummeryFactory(
                LeaveSummeryRepo(
                    RetrofitHelper.getInstance().create(ApiInterface::class.java),
                    pram
                ), this
            )
        ).get(LeaveSummeryViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        applyVM.liveData.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.NoInternet -> {
                    loadingDialog.dismiss()

                }
                is Response.Success -> {
                    loadingDialog.dismiss()

                    binding.txtEmpName.text = sharePref.getValueString("KEY_TITLE") +" "+ sharePref.getValueString("KEY_FIRST_NAME") + " " +sharePref.getValueString("KEY_LAST_NAME")
                    binding.txtTotalLeave.text = it.data?.data?.Total
                    binding.txtEarnLeave.text = it.data?.data?.Earned
                    binding.txtCasualLeave.text = it.data?.data?.Casual
                    binding.txtSickLeave.text = it.data?.data?.Sick
                    binding.txtRemainLeave.text = it.data?.data?.Remaining
                }
                is Response.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)
    }
}