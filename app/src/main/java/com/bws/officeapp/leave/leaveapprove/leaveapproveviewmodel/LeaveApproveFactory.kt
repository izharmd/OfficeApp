package com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LeaveApproveFactory(val repo: LeaveApproveRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LeaveApproveViewModel(repo,context) as T
    }
}