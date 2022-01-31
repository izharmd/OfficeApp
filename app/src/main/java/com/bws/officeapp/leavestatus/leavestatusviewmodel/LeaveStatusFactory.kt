package com.bws.officeapp.leavestatus.leavestatusviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LeaveStatusFactory(val repo: LeaveStatusRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return LeaveStatusViewModel(repo,context) as T
    }
}