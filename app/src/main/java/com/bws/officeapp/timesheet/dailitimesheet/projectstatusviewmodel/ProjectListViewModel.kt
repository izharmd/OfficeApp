package com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch

class ProjectListViewModel(val repository: ProjectStatusRepository, val context: Context) :
    ViewModel() {

    var liveData: MutableLiveData<Response<ProjectStausListResponse>>

    var liveDataProjectList: MutableLiveData<Response<ProjectListResponse>>

    init {
        liveData = MutableLiveData()
        liveDataProjectList = MutableLiveData()

        getCallProjectStatusList()
    }

    private fun getCallProjectStatusList() {

        viewModelScope.launch {
            liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if (NetworkUtils.isNetworkAvailable(context)) {

                try {
                    viewModelScope.launch {
                        val responseProjectList = repository.getProjectList()
                        val statusProjectList = responseProjectList.body()?.bStatus
                        if (statusProjectList == true) {
                            liveDataProjectList.postValue(Response.Success(responseProjectList.body()))
                        } else {
                            liveDataProjectList.postValue(Response.Error(errorMessage = responseProjectList.body()!!.sMessage))
                        }
                    }
                    val response = repository.getProjectStatusList()
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
                liveData.postValue(Response.Error(errorMessage = context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }

        }
    }
}