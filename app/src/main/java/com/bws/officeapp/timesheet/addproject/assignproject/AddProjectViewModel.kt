package com.bws.officeapp.timesheet.addproject.assignproject

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.officeapp.R
import com.bws.officeapp.utils.NetworkUtils
import com.bws.officeapp.utils.Response
import kotlinx.coroutines.launch

class AddProjectViewModel(val repo: AddProjectRepo,val context: Context):ViewModel() {

    var liveData:MutableLiveData<Response<AddProjectResponse>>

    init {
        liveData = MutableLiveData()
        getCallProjectStatusList()
    }


    private fun getCallProjectStatusList() {

        viewModelScope.launch {
            liveData.postValue(Response.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))
            if (NetworkUtils.isNetworkAvailable(context)) {
                try {
                    val response = repo.callAddProject()
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