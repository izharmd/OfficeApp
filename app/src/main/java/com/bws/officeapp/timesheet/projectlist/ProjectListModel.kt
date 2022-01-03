package com.bws.officeapp.timesheet.projectlist

data class ProjectListModel(
    val projectName: String,
    val alocatedBy: String,
    val projectStartDate: String,
    val projectEndDate: String,
    val totalSpendTime: String,
    val totalAggredTime: String,
    val projectStatus: String
)
