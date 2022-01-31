package com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectStatusFactory(
    val projectStatusRepository: ProjectStatusRepository,
    val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectListViewModel(projectStatusRepository, context) as T
    }
}