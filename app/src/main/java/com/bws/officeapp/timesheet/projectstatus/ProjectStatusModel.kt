package com.bws.timesheet.projectstatus

data class ProjectStatusModel(
    val projectName: String,
    val allocatedBy: String,
    val date: String,
    val agreedDate: String,
    val time: String,
    val status: String
)
