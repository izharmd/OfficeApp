package com.bws.officeapp.timesheet.searchproject.projectalocationsearch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectAllocationFactory(val repo: ProjectAllocationRepo, val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectAllocationSearchViewModel(repo, context) as T
    }
}

/*
class ProjectSearchByStatusFactory(val repo: ProjectAllocationRepoByProjectStatus,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectAllocarionByProjectStatusViewModel(repo,context) as T
    }
}
*/


class ProjectSearchByDateRngeFactory(val repo: ProjectAllocationRepoByProjectDateRange,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectAllocationByDateRangeViewModel(repo,context) as T
    }
}