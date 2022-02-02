package com.bws.officeapp.timesheet.searchproject

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
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivitySearchProjectBinding
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.bws.officeapp.leave.applyleave.LeaveActivity
import com.bws.officeapp.timesheet.ProfileTimeSheetActivity
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListFactory
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListModelView
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListRepo
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListResponse
import com.bws.officeapp.timesheet.projectlist.ProjectListActivity
import com.bws.officeapp.timesheet.searchproject.projectalocationsearch.*
import com.bws.officeapp.timesheet.searchproject.searchprojectviewmodel.SearchProjectFactory
import com.bws.officeapp.timesheet.searchproject.searchprojectviewmodel.SearchProjectRepo
import com.bws.officeapp.timesheet.searchproject.searchprojectviewmodel.SearchProjectViewModel
import com.bws.officeapp.utils.*
import com.devstune.searchablemultiselectspinner.SearchableItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_project.*
import kotlinx.android.synthetic.main.toolba_reminder.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class SearchProjectActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var arrProjectList = ArrayList<String>()
    var arrProjectSearchCategory = ArrayList<String>()
    var arrProjectStatus = ArrayList<String>()

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    lateinit var binding: ActivitySearchProjectBinding

    var itemSearchCategory = ""
    var itmProjectName = ""
    var searchFromDate = ""
    var searchToDate = ""

    lateinit var sharePref:SharedPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_project)
        supportActionBar?.hide()
        DateHeader().dateToHeader(this, textDate, textUserName, "Welcome To Office App")

        sharePref = SharedPreference(this)

        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)
        binding.spSearchProjectByCategory.onItemSelectedListener = this
        binding.spSearchByProjectName.onItemSelectedListener = this
        binding.spSearchByProjectStatus.onItemSelectedListener = this

        arrProjectSearchCategory.add("-Select-")
        arrProjectSearchCategory.add("By Project name")
        arrProjectSearchCategory.add("By Project Status")
        arrProjectSearchCategory.add("By Date Range")

        arrProjectStatus.add("InProgress")
        arrProjectStatus.add("Completed")
        arrProjectStatus.add("Not Completed")

        initView()

        txtDateFrom.setOnClickListener() {
            Common().dateDialog(this, binding.txtDateFrom)
        }
        txtDateTo.setOnClickListener() {
            Common().dateDialog(this, binding.txtDateTo)
        }

        txtSearch.setOnClickListener() {

            if(itemSearchCategory == "By Project name"){
                val pramSearchByProjectName = Param.PramSearchByProjectName(sharePref.getValueString("KEY_USER_ID").toString(),"By Project Name",itmProjectName,"100","1")
                val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)
                val projectListVM =
                    ViewModelProvider(
                        this,
                        ProjectAllocationFactory(ProjectAllocationRepo(api, pramSearchByProjectName), this)
                    ).get(
                        ProjectAllocationSearchViewModel::class.java
                    )

                val loadingDialog = LoadingDialog.progressDialog(this)

                projectListVM.liveData.observe(this, androidx.lifecycle.Observer {

                    when (it) {
                        is Response.NoInternet -> {
                            ToastMessage.message(this, it.noInternetMessage.toString())
                            clearViewModel()
                        }
                        is Response.Loading -> {
                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            loadingDialog.dismiss()
                            val result = it.data?.data?.ProjectList

                            //CONVER RESULT DATA IN TO JSON AND SEND TO NEXT ACTIVITY
                            val gson = Gson()
                            val intent = Intent(this, ProjectListActivity::class.java)
                            intent.putExtra("RESULT", gson.toJson(result).toString())
                            startActivity(intent)
                        }
                        is Response.Error -> {
                            ToastMessage.message(this, it.errorMessage.toString())
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                    }
                })

            }

            else if(itemSearchCategory == "By Project Status"){

                val pramSearchByProjectName = Param.PramSearchByProjectStatus(sharePref.getValueString("KEY_USER_ID").toString(),"By Project Status",itmProjectName,"100","1")
                val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)
                val projectListVM =
                    ViewModelProvider(
                        this,
                        ProjectSearchByStatusFactory(ProjectAllocationRepoByProjectStatus(api, pramSearchByProjectName), this)
                    ).get(
                        ProjectAllocarionByProjectStatusViewModel::class.java
                    )

                val loadingDialog = LoadingDialog.progressDialog(this)

                projectListVM.liveData.observe(this, androidx.lifecycle.Observer {

                    when (it) {
                        is Response.NoInternet -> {
                            ToastMessage.message(this, it.noInternetMessage.toString())
                            clearViewModel()
                        }
                        is Response.Loading -> {
                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            loadingDialog.dismiss()
                            val result = it.data?.data?.ProjectList

                            //CONVER RESULT DATA IN TO JSON AND SEND TO NEXT ACTIVITY
                            val gson = Gson()
                            val intent = Intent(this, ProjectListActivity::class.java)
                            intent.putExtra("RESULT", gson.toJson(result).toString())
                            startActivity(intent)
                        }
                        is Response.Error -> {
                            ToastMessage.message(this, it.errorMessage.toString())
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                    }
                })

            }

            else if(itemSearchCategory == "By Date Range"){

                val originalFormat = SimpleDateFormat("dd-MM-yyyy")
                var dateFrom: Date
                var dateTo: Date
                try {
                    dateFrom = originalFormat.parse(binding.txtDateFrom.text.toString())
                    dateTo = originalFormat.parse(binding.txtDateTo.text.toString())
                    searchFromDate = DateUtils.toSimpleString(dateFrom)
                    searchToDate = DateUtils.toSimpleString(dateTo)
                } catch (ex: ParseException) {
                    // Handle Exception.
                }


                val pramSearchByProjectName = Param.PramSearchByProjectDateRange(sharePref.getValueString("KEY_USER_ID").toString(),"By Date Range","2022-01-20","2022-03-29","100","1")
                val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)
                val projectListVM =
                    ViewModelProvider(
                        this,
                        ProjectSearchByDateRngeFactory(ProjectAllocationRepoByProjectDateRange(api, pramSearchByProjectName), this)
                    ).get(
                        ProjectAllocationByDateRangeViewModel::class.java
                    )

                val loadingDialog = LoadingDialog.progressDialog(this)

                projectListVM.liveData.observe(this, androidx.lifecycle.Observer {

                    when (it) {
                        is Response.NoInternet -> {
                            ToastMessage.message(this, it.noInternetMessage.toString())
                            clearViewModel()
                        }
                        is Response.Loading -> {
                            loadingDialog.show()
                        }
                        is Response.Success -> {
                            loadingDialog.dismiss()
                            val result = it.data?.data?.ProjectList

                            //CONVER RESULT DATA IN TO JSON AND SEND TO NEXT ACTIVITY
                            val gson = Gson()
                            val intent = Intent(this, ProjectListActivity::class.java)
                            intent.putExtra("RESULT", gson.toJson(result).toString())
                            startActivity(intent)
                        }
                        is Response.Error -> {
                            ToastMessage.message(this, it.errorMessage.toString())
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                    }
                })
            }
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

    private fun initView() {
        val pramProjectList = Param.PramProjectList("ProjectList")
        val api = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val projectListVM =
            ViewModelProvider(
                this,
                SearchProjectFactory(SearchProjectRepo(api, pramProjectList), this)
            ).get(
                SearchProjectViewModel::class.java
            )

        val loadingDialog = LoadingDialog.progressDialog(this)

        projectListVM.liveData.observe(this, androidx.lifecycle.Observer {

            when (it) {
                is Response.NoInternet -> {
                    ToastMessage.message(this, it.noInternetMessage.toString())
                }
                is Response.Loading -> {
                    loadingDialog.show()
                }
                is Response.Success -> {
                    loadingDialog.dismiss()
                    for (i in 0..it.data!!.data.size - 1) {
                        arrProjectList.add(it.data!!.data.get(i).ProjectName)
                    }
                    val adProjectName =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrProjectList)
                    adProjectName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spSearchByProjectName.setAdapter(adProjectName)


                    val adProjectCategory =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrProjectSearchCategory)
                    adProjectCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spSearchProjectByCategory.setAdapter(adProjectCategory)

                    val adProjectStatus =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, arrProjectStatus)
                    adProjectStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spSearchByProjectStatus.setAdapter(adProjectStatus)
                }
                is Response.Error -> {
                    ToastMessage.message(this, it.errorMessage.toString())
                    loadingDialog.dismiss()
                }
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>, arg1: View, position: Int, id: Long) {
        when (parent) {
            binding.spSearchProjectByCategory -> {
                if (position != 0) {
                     itemSearchCategory = arrProjectSearchCategory.get(position)
                    if (itemSearchCategory == "By Project name") {
                        binding.spSearchByProjectName.visibility = View.VISIBLE
                        binding.spSearchByProjectStatus.visibility = View.GONE
                        binding.llDateRange.visibility = View.GONE
                        binding.txtProjectSearchWith.text = "Search By Project Name"
                        binding.txtProjectSearchWith.visibility = View.VISIBLE
                    } else if (itemSearchCategory == "By Project Status") {
                        binding.spSearchByProjectName.visibility = View.GONE
                        binding.spSearchByProjectStatus.visibility = View.VISIBLE
                        binding.llDateRange.visibility = View.GONE
                        binding.txtProjectSearchWith.visibility = View.VISIBLE
                        binding.txtProjectSearchWith.text = "Search By Project Status"
                    } else if (itemSearchCategory == "By Date Range") {
                        binding.spSearchByProjectName.visibility = View.GONE
                        binding.spSearchByProjectStatus.visibility = View.GONE
                        binding.llDateRange.visibility = View.VISIBLE
                        binding.txtProjectSearchWith.text = "Search By Date Range"
                        binding.txtProjectSearchWith.visibility = View.VISIBLE
                    }
                }
            }
            binding.spSearchByProjectName -> {
                 itmProjectName = arrProjectList.get(position)
            }
            binding.spSearchByProjectStatus -> {
                var itmProjectStatus = arrProjectStatus.get(position)
            }
        }

        //Use for side popup menu
        MyPopUpMenu().populateMenuLeave(this,imv_Shutdown)
        //BACK TO PREVIOUS ACTIVITY
        MyPopUpMenu().backToActivity(this,imvBack)
    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
    object DateUtils {
        @JvmStatic
        fun toSimpleString(date: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(date)
        }
    }

}