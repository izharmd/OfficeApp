package com.bws.officeapp.leave.applyleave.applyviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApplyLeaveFactory(val applyLeaveRepo: ApplyLeaveRepo, val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApplyViewModel(applyLeaveRepo,context) as T
    }
}