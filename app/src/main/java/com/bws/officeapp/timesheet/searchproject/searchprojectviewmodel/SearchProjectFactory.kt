package com.bws.officeapp.timesheet.searchproject.searchprojectviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchProjectFactory(val repo: SearchProjectRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return SearchProjectViewModel(repo,context) as T
    }
}