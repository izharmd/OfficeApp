package com.bws.officeapp.registration

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityRegistrationBinding
import com.bws.officeapp.login.LoginActivity
import com.bws.officeapp.registration.designationviewmodel.DesignationFactory
import com.bws.officeapp.registration.designationviewmodel.DesignationPram
import com.bws.officeapp.registration.designationviewmodel.DesignationRepository
import com.bws.officeapp.registration.designationviewmodel.DesignationViewModel
import com.bws.officeapp.registration.registrationmodel.*
import com.bws.officeapp.registration.registrationviewmodel.RegistrationFactory
import com.bws.officeapp.registration.registrationviewmodel.RegistrationRepository
import com.bws.officeapp.registration.registrationviewmodel.RegistrationViewModel
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.activity_registration.*


import java.util.*

class RagistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    DatePickerDialog.OnDateSetListener {

    lateinit var registrationViewModel: RegistrationViewModel
    lateinit var bindingActivity: ActivityRegistrationBinding

    var list_of_designation =
        arrayOf("Select Designation", "Software Engineer", "Tester", "Project Cordinator")

    var selectDesignation: String? = null

    private var selection = "Male"

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var currentDay = 0
    var currentMonth: Int = 0
    var currentYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        bindingActivity.spDesignation!!.setOnItemSelectedListener(this)
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_designation)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bindingActivity.spDesignation!!.setAdapter(aa)

        initView()



        onClickEvent()
    }

    private fun initView() {

        val pram = DesignationPram("UserDesignationList")
        val designationApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val designationRepository = DesignationRepository(designationApi, pram)
        val designationFactory = DesignationFactory(designationRepository, this)
        val designationViewModel =
            ViewModelProvider(this, designationFactory).get(DesignationViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        designationViewModel.liveData.observe(this, Observer {
            when (it) {
                is Response.NoInternet -> {
                    Toast.makeText(this, "NO INTERNET", Toast.LENGTH_LONG).show()
                }
                is Response.Loading -> {

                    loadingDialog.show()
                }
                is Response.Success -> {

                    System.out.println("RESULT===" + it.data.toString())

                    val str = it.data?.data?.get(2)?.RoleName

                    System.out.println("RESULT===" + str)

                    Toast.makeText(this, it.data.toString(), Toast.LENGTH_LONG).show()
                    loadingDialog.dismiss()
                }
                is Response.Error -> {
                    Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()
                    loadingDialog.dismiss()
                }
            }
        })
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        currentDay = p3
        currentYear = p1
        currentMonth = p2 + 1

        // bindingActivity.txtDOB.setText(String.format("%02d",currentDay)+"-"+String.format("%02d",currentMonth)+"-"+currentYear)
    }


    private fun onClickEvent() {

        bindingActivity.rdGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rdMale -> {
                    selection = "Male"
                    ToastMessage.message(this, selection)
                }
                R.id.rdFemale -> {
                    selection = "Female"
                    ToastMessage.message(this, selection)
                }
            }
        }


        bindingActivity.txtDOB.setOnClickListener() {

            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(
                    this@RagistrationActivity,
                    this@RagistrationActivity,
                    year,
                    month,
                    day
                )
            datePickerDialog.show()
        }



        bindingActivity.btnRegister.setOnClickListener() {


            val isAllCheck = CheckAllFields()
           // if (isAllCheck) {

                /* val regisPram = PramRegistration(
                     "2",
                     bindingActivity.edtTitle.text.toString(),
                     bindingActivity.edtTitle.text.toString(),
                     bindingActivity.edtTitle.text.toString(),
                     bindingActivity.edtPhoneNo.text.toString(),
                     bindingActivity.edtEmail.text.toString(),
                     "2",
                     "1989.12.28",
                     "M",
                     bindingActivity.edtPassword.text.toString()
                 )*/

                val regisPram = PramRegistration(
                    "2",
                    "Mr",
                    "Izhar",
                    "Ansari",
                    "1236547896",
                    "izhar.md89@gmail.com",
                    "Android App Developer",
                    "1989.12.28",
                    "M",
                    "Test@123"
                )

                /*"Title":"Mr",
                "FirstName":"Izhar",
                "LastName":"Ansari",
                "MobileNo":"1236547896",
                "Emailid":"izhar.md89@gmail.com",
                "RoleID":"2",
                "Designation":"Android App Developer",
                "DOB":"1989.12.28",
                "Gender":"M",
                "Password":"Test@123"
*/
                System.out.println(regisPram.toString())

                val regFactory = RegistrationFactory(
                    RegistrationRepository(
                        RetrofitHelper.getInstance().create(ApiInterface::class.java), regisPram
                    ), this
                )
                registrationViewModel =
                    ViewModelProvider(this, regFactory).get(RegistrationViewModel::class.java)
                val loadingDialog = LoadingDialog.progressDialog(this)
                registrationViewModel.registrationLiveData.observe(this, Observer {
                    when (it) {
                        is Response.NoInternet -> {

                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                        is Response.Loading -> {

                            loadingDialog.show()
                        }
                        is Response.Success -> {

                           // ToastMessage.message(this@RagistrationActivity, "SSSSS")
                            Toast.makeText(this, it.data.toString(), Toast.LENGTH_LONG).show()
                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                        is Response.Error -> {

                            Toast.makeText(this, it.errorMessage.toString(), Toast.LENGTH_LONG).show()


                            loadingDialog.dismiss()
                            clearViewModel()
                        }
                    }
                })
          //  }
        }

        bindingActivity.llAllreadyAccount.setOnClickListener() {
            startActivity(Intent(this@RagistrationActivity, LoginActivity::class.java))
            finish()
        }
    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }


    fun CheckAllFields(): Boolean {
        if (bindingActivity.edtTitle.length() === 0) {
            bindingActivity.edtTitle.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }
        if (bindingActivity.edtFName.length() === 0) {
            bindingActivity.edtFName.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }
        if (bindingActivity.edtLName.length() === 0) {
            bindingActivity.edtLName.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtEmail.length() === 0) {
            bindingActivity.edtEmail.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtPhoneNo.length() === 0) {
            bindingActivity.edtPhoneNo.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }

        if (bindingActivity.edtPhoneNo.length() === 0) {
            bindingActivity.edtPhoneNo.setError(resources.getString(R.string.FIELD_ARE_REQUIRED))
            return false
        }


        if (bindingActivity.edtPassword.text.toString() != (bindingActivity.edtConfirmPassWord.text.toString())) {
            bindingActivity.edtPassword.setError("Password mismatch")
            return false
        }


        if (bindingActivity.edtConfirmPassWord.length() === 0) {
            bindingActivity.edtConfirmPassWord.setError("This field is required")
            return false
        }

        // after all validation return true.
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // selectDesignation  = parent?.selectedItem as Nothing?

        selectDesignation = parent?.getItemAtPosition(position).toString()
        Log.d("q23we4r5", "werty")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // TODO("Not yet implemented")
    }
}