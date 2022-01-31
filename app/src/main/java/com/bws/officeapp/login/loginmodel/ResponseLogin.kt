package com.bws.officeapp.login.loginmodel

data class ResponseLogin(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val DOB: String,
    val Designation: String,
    val EmailID: String,
    val FirstName: String,
    val LastName: String,
    val Message: String,
    val Title: String,
    val UserID: Int
)