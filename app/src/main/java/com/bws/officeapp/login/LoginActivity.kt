package com.bws.officeapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.RetrogitHelper
import com.bws.officeapp.DashboardOfficeAppActivity
import com.bws.officeapp.R
import com.bws.officeapp.databinding.ActivityLoginBinding
import com.bws.officeapp.login.loginmodel.LoginPram
import com.bws.officeapp.login.loginmodel.ResponseLogin
import com.bws.officeapp.login.loginviewmodel.LoginFactory
import com.bws.officeapp.login.loginviewmodel.LoginRepository
import com.bws.officeapp.login.loginviewmodel.LoginViewModel
import com.bws.officeapp.utils.LoadingDialog
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.ToastMessage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.ViewModel


class LoginActivity : AppCompatActivity() {

    // lateinit var loginViewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding

    //val loadingDialog = LoadingDialog.progressDialog(this)
    val loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        supportActionBar?.hide()
        binding.btnLogin.setOnClickListener() {

            val isAllCheck = CheckAllFields()
            // if (isAllCheck) {
            val loginPram = LoginPram(
                binding.edtUserName.text.toString(), binding.edtPassword.text.toString()
            )
            val loginApi = RetrogitHelper.getInstance().create(ApiInterface::class.java)
            val loginRepository = LoginRepository(loginApi, loginPram)
            val loginFactory = LoginFactory(loginRepository, this)
            val loginViewModel =
                ViewModelProvider(this, loginFactory).get(LoginViewModel::class.java)

            val loadingDialog = LoadingDialog.progressDialog(this)

            loginViewModel.loginResult.observe(this, Observer {
                when (it) {
                    is Response.NoInternet -> {
                        ToastMessage.message(this, it?.noInternetMessage.toString())
                        loadingDialog.dismiss()
                    }
                    is Response.Loading -> {
                        loadingDialog.show()
                    }
                    is Response.Success -> {
                        loadingDialog.dismiss()
                        it.data.let {
                            ToastMessage.message(this, it.toString())
                        }
                        clearViewModel()
                    }
                    is Response.Error -> {
                        loadingDialog.dismiss()
                        ToastMessage.message(this, it.errorMessage.toString())
                    }
                }
            })
        }

    }
    //CLEAR VIEW MODEL IF LIVE DATA IS AVAILABLE
    fun clearViewModel() {
        this.viewModelStore.clear()
    }

    fun CheckAllFields(): Boolean {
        if (binding.edtUserName.length() === 0) {
            binding.edtUserName.setError("This field is required")
            return false
        }
        if (binding.edtPassword.length() === 0) {
            binding.edtPassword.setError("This field is required")
            return false
        }
        // after all validation return true.
        return true
    }
}