package com.bws.officeapp.timesheet.addproject.userlistmodel

data class ResponseProjectList(
    val bStatus: Boolean,
    val `data`: List<DataProjectList>,
    val sMessage: String,
    val sStatus: String
)

data class DataProjectList(
    val ProjectID: Int,
    val ProjectName: String
)