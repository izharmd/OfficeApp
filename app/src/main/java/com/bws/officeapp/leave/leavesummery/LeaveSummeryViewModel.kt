package com.bws.officeapp.leave.leavesummery

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils

import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch


class LeaveSummeryViewModel(val repo: LeaveSummeryRepo, val context: Context) : ViewModel() {

    var liveData: MutableLiveData<Response<LeaveSummeryResponse>>

    init {
        liveData = MutableLiveData()
        getLeaveSummery()
    }

    private fun getLeaveSummery() {
        viewModelScope.launch {
            liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if (NetworkUtils.isNetworkAvailable(context)) {
                try {
                    val response = repo.getUserLeaveSummery()
                    val status = response.body()?.bStatus
                    if (status == true) {
                        liveData.postValue(Response.Success(response.body()))
                    } else {
                        liveData.postValue(Response.Error(errorMessage = response.body()!!.sMessage))
                    }
                } catch (e: Exception) {
                    liveData.postValue(Response.Error(errorMessage = e.message.toString()))
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