package com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel

data class DailyTimeSheetResponse(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val Message: String
)