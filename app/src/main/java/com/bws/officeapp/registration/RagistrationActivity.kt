package com.bws.officeapp.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrofitHelper
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityRegistrationBinding
import com.bws.officeapp.login.LoginActivity
import com.bws.officeapp.registration.registrationmodel.PramRegistration
import com.bws.officeapp.registration.registrationviewmodel.RegistrationFactory
import com.bws.officeapp.registration.registrationviewmodel.RegistrationRepository
import com.bws.officeapp.registration.registrationviewmodel.RegistrationViewModel
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response

class RagistrationActivity : AppCompatActivity() {

    lateinit var registrationViewModel: RegistrationViewModel
    lateinit var bindingActivity: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        onClickEvent()
    }

    private fun onClickEvent() {

        bindingActivity.btnRegister.setOnClickListener() {

            val regisPram = PramRegistration("", "", "", "", "", "", "", "", "")

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

                        loadingDialog.dismiss()
                        clearViewModel()
                    }
                    is Response.Error -> {

                        loadingDialog.dismiss()
                        clearViewModel()
                    }
                }
            })
        }

        bindingActivity.llAllreadyAccount.setOnClickListener(){
            startActivity(Intent(this@RagistrationActivity,LoginActivity::class.java))
            finish()
        }
    }

    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }
}