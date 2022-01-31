package com.bws.officeapp.login.loginmodel

data class LoginPram(
    val EmailId: String,
    val Password: String,
    val SessionID: String,
    val DeviceToken: String,
    val IPAddress: String,
    val DeviceLocation: String,
    val DeviceID: String
)

data class PramProjectStatusList(val Type: String)

