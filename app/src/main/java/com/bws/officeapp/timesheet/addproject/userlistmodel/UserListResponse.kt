package com.bws.officeapp.timesheet.addproject.userlistmodel

data class UserListResponse(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val UserID_FK: Int,
    val UserName: String
)