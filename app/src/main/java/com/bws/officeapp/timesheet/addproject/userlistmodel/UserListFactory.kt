package com.bws.officeapp.timesheet.addproject.userlistmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListFactory(val repo: UserListRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListModelView(repo,context) as T
    }
}