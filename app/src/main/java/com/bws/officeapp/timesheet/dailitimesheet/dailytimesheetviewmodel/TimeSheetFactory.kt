package com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TimeSheetFactory(val timeSheetRepo: TimeSheetRepo,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TimesheetViewModel(timeSheetRepo,context) as T
    }
}