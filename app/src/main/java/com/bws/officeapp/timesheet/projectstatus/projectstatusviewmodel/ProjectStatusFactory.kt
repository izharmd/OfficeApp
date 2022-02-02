package com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectStatusFactory(val repo: ProjectStatusRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectStatusViewModel(repo,context) as T
    }
}