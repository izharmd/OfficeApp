package com.bws.officeapp.login.loginmodel

data class ResponseLogin(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val Message: String,
    val UserID: Int,
    val EmailID: String,
    val Title: String,
    val FirstName: String,
    val LastName: String,
    val Designation: String,
    val DOB: String,
    val RoleID: Int,
    val RoleName: String,
    val MobileNo: Int,
    val DOJ: String,
)
