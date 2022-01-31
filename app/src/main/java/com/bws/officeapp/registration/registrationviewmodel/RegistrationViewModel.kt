package com.bws.officeapp.registration.registrationviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.registration.registrationmodel.ResgistrationResponse
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch


class RegistrationViewModel(
    val registrationRepository: RegistrationRepository,
    val context: Context
) : ViewModel() {
    val registrationLiveData = MutableLiveData<Response<ResgistrationResponse>>()

    init {
        getUserRegistration()
    }

    private fun getUserRegistration() {
        viewModelScope.launch {
            if (NetworkUtils.isNetworkAvailable(context)) {
                registrationLiveData.postValue(
                    Response.Loading(
                        loadingMessage = context.resources.getString(
                            R.string.LOADING_PLEASE_WAIT
                        )
                    )
                )
                try {
                    val response = registrationRepository.getUserRegistration()

                    val status = response.body()?.bStatus

                    if (status == true) {
                        registrationLiveData.postValue(Response.Success(response.body()))
                    } else {
                        registrationLiveData.postValue(Response.Error(errorMessage = response.body()!!.sMessage))
                    }
                } catch (e: Exception) {
                    registrationLiveData.postValue(Response.Error(e.message.toString()))
                }
            } else {
                registrationLiveData.postValue(Response.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }
        }
    }
}