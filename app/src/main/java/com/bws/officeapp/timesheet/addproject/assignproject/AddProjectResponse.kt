package com.bws.officeapp.timesheet.addproject.assignproject

data class AddProjectResponse(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val Message: String
)