package com.bws.officeapp.timesheet.addproject.assignproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddProjectFactory(val repo: AddProjectRepo, val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddProjectViewModel(repo, context) as T
    }
}