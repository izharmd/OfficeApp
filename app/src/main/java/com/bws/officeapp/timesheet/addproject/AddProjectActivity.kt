package com.bws.officeapp.timesheet.addproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityAddProjectBinding
import com.bws.officeapp.timesheet.ProfileTimeSheetActivity
import com.bws.officeapp.timesheet.addproject.assignproject.AddProjectFactory
import com.bws.officeapp.timesheet.addproject.assignproject.AddProjectRepo
import com.bws.officeapp.timesheet.addproject.assignproject.AddProjectViewModel
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListFactory
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListModelView
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListRepo
import com.bws.officeapp.utils.*
import com.devstune.searchablemultiselectspinner.SearchableItem
import com.devstune.searchablemultiselectspinner.SearchableMultiSelectSpinner
import com.devstune.searchablemultiselectspinner.SelectionCompleteListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_project.*

import kotlinx.android.synthetic.main.toolba_reminder.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddProjectActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var userListID = ArrayList<Param.Allocatedlist>()
    var arrProjectName = ArrayList<String>()
    var arrProjectID = ArrayList<String>()
    lateinit var binding: ActivityAddProjectBinding
    var userList: MutableList<SearchableItem> = ArrayList()
    lateinit var arrMap: ArrayMap<String, String>

    var strProjectId = ""
    var totalMinutes = ""

    lateinit var sharePfre: SharedPreference

    private var _year = 0
    private var _month = 0
    private var _day = 0
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_project)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)

        sharePfre = SharedPreference(this)
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

    private fun clickEvent() {

        binding.txtStartDate.setOnClickListener() {
            Common().dateDialog(this, binding.txtStartDate)
        }
        binding.txtAgreedDeliveryDate.setOnClickListener() {
           // Common().dateDialog(this, binding.txtAgreedDeliveryDate)

            calendar = Calendar.getInstance()
            _year = calendar.get(Calendar.YEAR)
            _month = calendar.get(Calendar.MONTH)
            _day = calendar.get(Calendar.DAY_OF_MONTH)
            val dialog = DatePickerDialog(this, { _, year, month, day_of_month ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = day_of_month
                val myFormat = "dd-MM-yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())

                binding.txtAgreedDeliveryDate.text = sdf.format(calendar.time)
                calculateAgreedTime(
                    binding.txtStartDate.text.toString(),
                    binding.txtAgreedDeliveryDate.text.toString()
                )
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
            //dialog.datePicker.minDate = calendar.timeInMillis
            // calendar.add(Calendar.YEAR, 0)
            // dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()


        }

        binding.txtSave.setOnClickListener() {
            val originalFormat = SimpleDateFormat("dd-MM-yyyy")
            var sDate: Date
            var dDate: Date
            try {
                sDate = originalFormat.parse(binding.txtStartDate.text.toString())
                dDate = originalFormat.parse(binding.txtAgreedDeliveryDate.text.toString())
                val startDate = DateUtils.toSimpleString(sDate)
                val deliveryDate = DateUtils.toSimpleString(dDate)
                val prm = Param.PramAddProject(
                    deliveryDate,
                    totalMinutes,
                    userListID,
                    strProjectId,
                    startDate,
                    sharePfre.getValueString("KEY_USER_ID").toString()
                )
                val jsonString = Gson().toJson(prm)
                System.out.println("JSON==" + jsonString)

                val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)

                val addProjectVM =
                    ViewModelProvider(
                        this,
                        AddProjectFactory(AddProjectRepo(api, prm), this)
                    ).get(
                        AddProjectViewModel::class.java
                    )

                val loadingDialog = LoadingDialog.progressDialog(this)

                addProjectVM.liveData.observe(this, Observer {
                    when (it) {
                        is Response.NoInternet -> {
                            ToastMessage.message(this, it.noInternetMessage.toString())
                        }
                        is Response.Loading -> {
                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            ToastMessage.message(this, it.data?.sMessage.toString())
                            loadingDialog.dismiss()
                        }
                        is Response.Error -> {
                            ToastMessage.message(this, it.errorMessage.toString())
                            loadingDialog.dismiss()
                        }
                    }
                })


            } catch (ex: ParseException) {
                // Handle Exception.
            }
        }
    }

    private fun initView() {

        binding.spinner1!!.setOnItemSelectedListener(this)

        val pram = Param.PramUserList("UserList")
        val pramProjectList = Param.PramProjectList("ProjectList")
        val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        val userLitVM =
            ViewModelProvider(
                this,
                UserListFactory(UserListRepo(api, pram, pramProjectList), this)
            ).get(
                UserListModelView::class.java
            )

        val loadingDialog = LoadingDialog.progressDialog(this)

        userLitVM.liveData.observe(this, Observer {
            when (it) {
                is Response.NoInternet -> {
                    ToastMessage.message(this, it.noInternetMessage.toString())
                }
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.Success -> {
                    arrMap = ArrayMap()
                    for (i in 0..it.data!!.data.size - 1) {
                        userList.add(SearchableItem(it.data!!.data.get(i).UserName, i.toString()))
                        arrMap?.put(
                            it.data!!.data.get(i).UserName,
                            it.data!!.data.get(i).UserID_FK.toString()
                        )
                    }
                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    ToastMessage.message(this, it.errorMessage.toString())
                    loadingDialog.dismiss()
                }
            }
        })



        userLitVM.projectList.observe(this, Observer {
            when (it) {
                is Response.NoInternet -> {
                    ToastMessage.message(this, it.noInternetMessage.toString())
                }
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.Success -> {
                    for (i in 0..it.data!!.data.size - 1) {
                        arrProjectName.add(it.data!!.data.get(i).ProjectName)
                        arrProjectID.add(it.data!!.data.get(i).ProjectID.toString())
                    }

                    val aa =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrProjectName)
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner1!!.setAdapter(aa)


                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    ToastMessage.message(this, it.errorMessage.toString())
                    loadingDialog.dismiss()
                }
            }
        })

        binding.txtAllocatedTo.setOnClickListener() {
            SearchableMultiSelectSpinner.show(this, "Search Employee", "Done", userList, object :
                SelectionCompleteListener {
                override fun onCompleteSelection(selectedItems: ArrayList<SearchableItem>) {
                    println("DATA====" + selectedItems.toString())
                    var arr = ArrayList<String>()
                    for (i in 0..selectedItems.size - 1) {
                        val itm = selectedItems.get(i)
                        arr.add(itm.text)
                    }
                    var str = arr.toString()
                    binding.txtAllocatedTo.text = str
                    for (i in 0..arr.size - 1) {
                        val userID = arrMap.get(arr.get(i))
                        val lst = Param.Allocatedlist(userID.toString())
                        userListID.add(lst)
                    }
                }
            })
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinner1 -> {
                strProjectId = arrProjectID.get(position).toString()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun calculateAgreedTime(strStartDate: String, strDeliveryDate: String) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val sDate: Date = sdf.parse(strStartDate)
        val dDate: Date = sdf.parse(strDeliveryDate)
        val diff: Long = dDate.getTime() - sDate.getTime()
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        totalMinutes = minutes.toString()
        val days = hours / 24 + 1
        val hr = hours % 24
        println("DAY==" + days)

        binding.txtAgreedTime.text = days.toString() + " d " /*+ hr.toString() + " h "*/

    }

    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date): String {
            val format = SimpleDateFormat("yyyy.MM.dd")
            return format.format(date)
        }
    }

}