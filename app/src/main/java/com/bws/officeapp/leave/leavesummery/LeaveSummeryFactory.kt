package com.bws.officeapp.leave.leavesummery

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LeaveSummeryFactory(val repo: LeaveSummeryRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LeaveSummeryViewModel(repo,context) as T
    }
}