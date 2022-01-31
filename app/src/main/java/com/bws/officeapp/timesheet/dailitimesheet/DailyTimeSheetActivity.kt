package com.bws.officeapp.timesheet.dailitimesheet

import android.app.DatePickerDialog
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityDailyTimeSheetBinding
import com.bws.officeapp.login.loginmodel.PramProjectStatusList
import com.bws.officeapp.registration.designationviewmodel.DesignationFactory
import com.bws.officeapp.registration.designationviewmodel.DesignationPram
import com.bws.officeapp.registration.designationviewmodel.DesignationRepository
import com.bws.officeapp.registration.designationviewmodel.DesignationViewModel
import com.bws.officeapp.timesheet.ProfileTimeSheetActivity
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectListViewModel
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectStatusFactory
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectStatusRepository
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import kotlinx.android.synthetic.main.activity_daily_time_sheet.*
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.util.*
import kotlin.collections.ArrayList


import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import java.text.SimpleDateFormat
import android.R.string.no
import java.sql.Time
import java.time.Duration
import android.R.string.no
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Period
import android.R.string.no
import android.R.string.no
import android.R.string.no
import java.util.concurrent.TimeUnit
import android.R.string.no
import com.bws.officeapp.Api.Param
import com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel.TimeSheetFactory
import com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel.TimeSheetRepo
import com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel.TimesheetViewModel
import com.bws.officeapp.utils.SharedPreference
import com.bws.officeapp.utils.ToastMessage
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DailyTimeSheetActivity : AppCompatActivity()/*, AdapterView.OnItemSelectedListener*/ {
    var arrListProjectName = ArrayList<String>()
    var arrListProjectID = ArrayList<String>()
    var projectID = ""
    val arrListProStatus = ArrayList<String>()
    val arrListProStatusID = ArrayList<String>()
    var projectStatusID = ""

    var startDate = ""

    lateinit var binding: ActivityDailyTimeSheetBinding

    var cal = Calendar.getInstance()

    var currentDate = ""
    var startHr: Int = 0
    var startMin: Int = 0

    var totalWorkingMinutes = ""
    var totalOverTimeMinutes = ""

    lateinit var sharedPreference: SharedPreference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_time_sheet)
        // setContentView(R.layout.activity_daily_time_sheet)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        sharedPreference = SharedPreference(this)

        sharedPreference.getValueString("")

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate1 = sdf.format(Date())
        binding.txtDate.text = currentDate1.toString()


//DAY START TIME
        val calStartTime = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calStartTime.set(Calendar.HOUR_OF_DAY, hour)
            calStartTime.set(Calendar.MINUTE, minute)
            txtDayStartTime.text = SimpleDateFormat("hh:mm a").format(calStartTime.time)

            startHr = hour
            startMin = minute

            val sdf = SimpleDateFormat("dd/M/yyyy")
            currentDate = sdf.format(Date())
            System.out.println(" C DATE is  " + currentDate)
        }
        binding.txtDayStartTime.setOnClickListener() {
            TimePickerDialog(
                this,
                timeSetListener,
                calStartTime.get(Calendar.HOUR_OF_DAY),
                calStartTime.get(Calendar.MINUTE),
                false
            ).show()

        }


        //DAY END TIME
        val calEndTime = Calendar.getInstance()
        val dayEndTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calEndTime.set(Calendar.HOUR_OF_DAY, hour)
            calEndTime.set(Calendar.MINUTE, minute)
            txtDayEndTime.text = SimpleDateFormat("hh:mm a").format(calEndTime.time)


            val date = SimpleDateFormat("hh:mm a").parse(txtDayStartTime.text.toString())
            val date1 = SimpleDateFormat("hh:mm a").parse(txtDayEndTime.text.toString())


            val diff: Long = date1.getTime() - date.getTime()
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val mint = minutes % 60

            totalWorkingMinutes = minutes.toString()

            val workingTime = 9 * 60

            binding.txtTotalWorkingTime.text = hours.toString() + " h " + mint.toString() + " min"

            if (minutes > workingTime) {
                val overTime = minutes.toInt() - workingTime
                val overTimeHours = overTime / 60
                val overTimeMinutes = overTime % 60

                totalOverTimeMinutes = overTimeMinutes.toString()
                System.out.println("OVER TIME==" + totalOverTimeMinutes)
                binding.txtOverTime.text =
                    overTimeHours.toString() + " h " + overTimeMinutes + " min"

            } else {
                binding.txtOverTime.text = "00"
            }
        }
        binding.txtDayEndTime.setOnClickListener() {
            TimePickerDialog(
                this,
                dayEndTimeSetListener,
                calEndTime.get(Calendar.HOUR_OF_DAY),
                calEndTime.get(Calendar.MINUTE),
                false
            ).show()
        }

//DATE PICKER DIALOG
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        binding.txtDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@DailyTimeSheetActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        })


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
                                ProfileTimeSheetActivity::class.java
                            )
                        )
                    R.id.logOut ->
                        // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe", "qewrt");

                }
                true
            })
            popupMenu.show()
        }

        imvBack.setOnClickListener() {
            finish()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.txtDate!!.text = sdf.format(cal.getTime())
    }

    private fun clickEvent() {

        binding.btnSave.setOnClickListener() {

            if (totalWorkingMinutes == "") {
                totalWorkingMinutes = "9"
            }
            if (totalOverTimeMinutes == "") {
                totalOverTimeMinutes = "0"
            }

            val originalFormat = SimpleDateFormat("dd-MM-yyyy")
            val targetFormat = SimpleDateFormat("MM.dd.yyyy")
            var date: Date
            try {
                date = originalFormat.parse(txtDate.text.toString())
                println("Old Format :   " + originalFormat.format(date))
                println("New Format :   " + targetFormat.format(date))

                var dt = targetFormat.format(date).toString()
                println("New Format :   " + dt)

                startDate = DateUtils.toSimpleString(date)
            } catch (ex: ParseException) {
                // Handle Exception.
            }


            val pram = Param.PramDailyTimeSheetDetails(
                sharedPreference.getValueString("KEY_USER_ID").toString(),
                projectStatusID, //===========
                projectID,
                startDate,
                binding.txtDayStartTime.text.toString(),
                binding.txtDayEndTime.text.toString(),
                totalWorkingMinutes,
                totalOverTimeMinutes   //======
            )

            System.out.println("PRAM===" + pram.toString())

            val dailyTimeSheetVM = ViewModelProvider(
                this,
                TimeSheetFactory(
                    TimeSheetRepo(
                        RetrofitHelper.getInstance().create(ApiInterface::class.java), pram
                    ), this
                )
            ).get(TimesheetViewModel::class.java)

            val loadingDialog = LoadingDialog.progressDialog(this)
            dailyTimeSheetVM.liveData.observe(this, Observer {

                when (it) {
                    is Response.NoInternet -> {
                        loadingDialog.dismiss()
                        clearViewModel()
                    }

                    is Response.Loading -> {
                        loadingDialog.show()
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

    }

    private fun initView() {

        val pram = PramProjectStatusList("ProjectStatusList")
        val pramProjectList = Param.PramProjectList("ProjectList")
        val apiProjectStatus = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val projectStatusRepo = ProjectStatusRepository(apiProjectStatus, pram, pramProjectList)
        val projectStatusFactory = ProjectStatusFactory(projectStatusRepo, this)
        val projectStatusViewModel =
            ViewModelProvider(this, projectStatusFactory).get(ProjectListViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        projectStatusViewModel.liveDataProjectList.observe(this, Observer {
            when (it) {
                is Response.Success -> {
                    for (i in 0..it.data!!.data.size - 1) {
                        arrListProjectName.add(it.data!!.data.get(i).ProjectName)
                        arrListProjectID.add(it.data!!.data.get(i).ProjectID.toString())
                    }
                    val aa =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrListProjectName)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerProject!!.setAdapter(aa)
                }

                is Response.Error -> {
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

        projectStatusViewModel.liveData.observe(this, Observer {
            when (it) {
                is Response.NoInternet -> {
                    Toast.makeText(this, "NO INTERNET", Toast.LENGTH_LONG).show()
                }
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.Success -> {
                    System.out.println("STATUS====" + it.data!!.data.size)
                    for (i in 0..it.data!!.data.size - 1) {
                        System.out.println("STATUS====" + it.data!!.data.get(i).ProjectStatus)
                        arrListProStatus.add(it.data!!.data.get(i).ProjectStatus)
                        arrListProStatusID.add(it.data!!.data.get(i).ProjectStatusID.toString())
                    }
                    val status =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrListProStatus)
                    status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerProjectStatus!!.setAdapter(status)
                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()
                    loadingDialog.dismiss()
                }
            }
        })

        binding.spinnerProjectStatus?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    projectStatusID = arrListProStatusID.get(position)
                    System.out.println("IDD==" + projectStatusID)
                }
            }

        binding.spinnerProject?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    projectID = arrListProjectID.get(position)
                }
            }
    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }

    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(date)
        }
    }
}