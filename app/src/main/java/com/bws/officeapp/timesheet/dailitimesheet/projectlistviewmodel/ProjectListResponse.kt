package com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel

data class ProjectListResponse(
    val bStatus: Boolean,
    val `data`: List<DataProjectList>,
    val sMessage: String,
    val sStatus: String
)

data class DataProjectList(
    val ProjectID: Int,
    val ProjectName: String
)