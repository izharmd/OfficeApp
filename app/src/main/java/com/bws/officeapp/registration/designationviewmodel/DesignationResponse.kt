package com.bws.officeapp.registration.designationviewmodel

data class DesignationResponse(
    val bStatus: Boolean,
    val `data`: List<Data>,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val RoleID: Int,
    val RoleName: String
)