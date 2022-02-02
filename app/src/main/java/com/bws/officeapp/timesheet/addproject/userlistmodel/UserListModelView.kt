package com.bws.officeapp.timesheet.addproject.userlistmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch

class UserListModelView(val repo: UserListRepo, val context: Context) : ViewModel() {

    var liveData: MutableLiveData<Response<UserListResponse>>
    var projectList: MutableLiveData<Response<ResponseProjectList>>

    init {
        liveData = MutableLiveData()
        projectList = MutableLiveData()
        getuserList()
    }

    private fun getuserList() {
        viewModelScope.launch {
            liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if (NetworkUtils.isNetworkAvailable(context)) {
                try {
                    viewModelScope.launch {
                        val responseProjectList = repo.callProjectListForAppProject()
                        val statusProjectList = responseProjectList.body()?.bStatus
                        if (statusProjectList == true) {
                            projectList.postValue(Response.Success(responseProjectList.body()))
                        } else {
                            projectList.postValue(Response.Error(errorMessage = responseProjectList.body()!!.sMessage))
                        }
                    }

                    val response = repo.callUserList()
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
                liveData.postValue(Response.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }
        }
    }
}