package com.bws.officeapp.leave.applyleave

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.ProfileActivity
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityLeaveBinding
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveFactory
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyLeaveRepo
import com.bws.officeapp.leave.applyleave.applyviewmodel.ApplyViewModel
import com.bws.officeapp.timesheet.dailitimesheet.DailyTimeSheetActivity
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectListViewModel
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectStatusFactory
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectStatusRepository
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference
import com.bws.officeapp.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_daily_time_sheet.*
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class LeaveActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var list_of_items = arrayOf("Earned Leave", "Casual Leave", "Sick Leave")
    var list_FromDate = arrayOf("Full Day", "Half Day")
    var list_ToDate = arrayOf("Full Day", "Half Day")

    lateinit var binding: ActivityLeaveBinding

    lateinit var sharePref: SharedPreference

    var IsLeaveFromHalfDay = ""
    var IsLeaveToHalfDay = ""
    var leaveTypeID = ""

    var leaveFromDate = ""
    var leaveToDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_leave)

        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_LEAVE_APP)

        sharePref = SharedPreference(this)

        initView()

        clickEvent()

        imv_Shutdown.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, imv_Shutdown)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.setting ->
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileActivity::class.java
                            )
                        )
                    R.id.logOut ->
                        // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe", "qewrt")

                }
                true
            })
            popupMenu.show()
        }
    }

    private fun clickEvent() {

        binding.txtFromDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.txtFromDate.text = "" + dayOfMonth + "-" + month + 1 + "-" + year
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        binding.txtToDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.txtToDate.text = "" + dayOfMonth + "-" + month + 1 + "-" + year
                },
                year,
                month,
                day
            )
            dpd.show()

        }


        binding.btnApplyLeave.setOnClickListener() {
            val isAllCheck = CheckAllFields()
             if (isAllCheck) {
                 applyLeave()
             }
        }
    }

    private fun applyLeave() {

        val originalFormat = SimpleDateFormat("dd-MM-yyyy")
        var dateFrom: Date
        var dateTo: Date
        try {
            dateFrom = originalFormat.parse(binding.txtFromDate.text.toString())
            dateTo = originalFormat.parse(binding.txtToDate.text.toString())
            leaveFromDate = DateUtils.toSimpleString(dateFrom)
            leaveToDate = DateUtils.toSimpleString(dateTo)
        } catch (ex: ParseException) {
            // Handle Exception.
        }

        val pramApplyLeave = Param.PramApplyLeave(
            binding.txtEmpId.text.toString(),
            leaveTypeID,
            "2",
            leaveFromDate,
            IsLeaveFromHalfDay,
            leaveToDate,
            IsLeaveToHalfDay,
            binding.edtReason.text.toString(),
            "Y"
        )

        val applyVM = ViewModelProvider(
            this,
            ApplyLeaveFactory(
                ApplyLeaveRepo(
                    RetrofitHelper.getInstance().create(ApiInterface::class.java),
                    pramApplyLeave
                ), this
            )
        ).get(ApplyViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        applyVM.liveData.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.NoInternet -> {
                    loadingDialog.dismiss()
                    clearViewModel()
                }
                is Response.Success -> {
                    loadingDialog.dismiss()
                    ToastMessage.message(this, it.data?.sMessage.toString())
                    clearViewModel()
                }
                is Response.Error -> {
                    loadingDialog.dismiss()
                    clearViewModel()
                }
            }
        })
    }

    private fun initView() {
        binding.txtEmpId.text = sharePref.getValueString("KEY_USER_ID")
        binding.txtEmpName.text =
            sharePref.getValueString("KEY_FIRST_NAME") + " " + sharePref.getValueString("KEY_LAST_NAME")
        binding.txtDesignation.text = sharePref.getValueString("KEY_DESIGNATION")


        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate1 = sdf.format(Date())
        binding.txtFromDate.text = currentDate1.toString()
        binding.txtToDate.text = currentDate1.toString()


        spinner!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)


        spFromDate!!.setOnItemSelectedListener(this)
        val adapterFromDate =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, list_FromDate)
        adapterFromDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFromDate!!.setAdapter(adapterFromDate)

        spToDate!!.setOnItemSelectedListener(this)
        val adapterToDate = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_ToDate)
        adapterToDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spToDate!!.setAdapter(adapterToDate)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spFromDate -> {
                if (position == 0) {
                    IsLeaveFromHalfDay = "N"
                } else {
                    IsLeaveFromHalfDay = "Y"
                }
            }
            binding.spToDate -> {
                if (position == 0) {
                    IsLeaveToHalfDay = "N"
                } else {
                    IsLeaveToHalfDay = "Y"
                }
            }
            binding.spinner -> {
                leaveTypeID = (position + 1).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }

    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date): String {
            val format = SimpleDateFormat("MM.dd.yyy")
            return format.format(date)
        }
    }

    fun CheckAllFields(): Boolean {
        if (binding.txtFromDate.length() == 0) {
           ToastMessage.message(this,"Please select from date")
            return false
        }
        if (binding.txtToDate.length() == 0) {
            ToastMessage.message(this,"Please select to date")
            return false
        }

        if (binding.edtReason.length() === 0) {
            ToastMessage.message(this,"Please enter reason")
            return false
        }
        // after all validation return true.
        return true
    }
}