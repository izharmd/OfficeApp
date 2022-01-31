package com.bws.officeapp.registration.designationviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch


class DesignationViewModel(val designationRepository: DesignationRepository, val context: Context) :
    ViewModel() {

    var liveData: MutableLiveData<Response<DesignationResponse>>

    init {
        liveData = MutableLiveData()

        getDesignation()
    }

    private fun getDesignation() {
        viewModelScope.launch {
            if (NetworkUtils.isNetworkAvailable(context)) {


                liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
                try {
                    val response = designationRepository.getDesignationList()
                    if (response != null) {
                        liveData.postValue(Response.Success(response.body()))
                    } else {
                        liveData.postValue(Response.Error(errorMessage = response))
                    }
                } catch (e: Exception) {
                    liveData.postValue(Response.Error(errorMessage = e.toString()))
                }
            } else {
                liveData.postValue(
                    Response.NoInternet(
                        noInternetMessage = context.resources.getString(
                            R.string.NO_INTERNET_CONNECTION
                        )
                    )
                )
            }
        }
    }

}