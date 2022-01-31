package com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel

data class ProjectStausListResponse(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val ProjectStatus: String,
    val ProjectStatusID: Int
)