package com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch

class TimesheetViewModel(val repo: TimeSheetRepo,val context: Context):ViewModel() {

    var liveData:MutableLiveData<Response<DailyTimeSheetResponse>>
    init {
        liveData = MutableLiveData()
        getDailyTimeSheet()
    }
    private fun getDailyTimeSheet() {
        viewModelScope.launch {
            liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if(NetworkUtils.isNetworkAvailable(context)){
                val response = repo.callDailyTimeSheetDetails()
                val status = response.body()?.bStatus
                if(status == true){
                    liveData.postValue(Response.Success(response.body()))
                }else{
                    liveData.postValue(Response.Error(errorMessage = response.body()!!.sMessage))
                }
            }else{
                liveData.postValue(Response.Error(errorMessage = context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }
        }
    }
}