package com.bws.officeapp.login.loginviewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.login.loginmodel.ResponseLogin
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch

class LoginViewModel(val loginRepository: LoginRepository, val context: Context) : ViewModel() {

    //val loginResult = MutableLiveData<Response<ResponseLogin>>()
     var loginResult:MutableLiveData<Response<ResponseLogin>>

    init {
        loginResult = MutableLiveData()
        getUserLogin()
    }

    private fun getUserLogin() {
        viewModelScope.launch {
            loginResult.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if (NetworkUtils.isNetworkAvailable(context)) {
                try {
                    val response = loginRepository.getUserLogin()
                    if (response != null) {
                        loginResult.postValue(Response.Success(response.body()))
                    }
                } catch (e: Exception) {
                    loginResult.postValue(Response.Error(errorMessage = e.message.toString()))
                }
            } else {
                loginResult.postValue(Response.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }
        }
    }

}