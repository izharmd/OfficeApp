package com.bws.officeapp.timesheet.searchproject.projectalocationsearch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectSearchByStatusFactory (val repo: ProjectAllocationRepoByProjectStatus,val context: Context):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectAllocarionByProjectStatusViewModel(repo,context) as T
    }
}