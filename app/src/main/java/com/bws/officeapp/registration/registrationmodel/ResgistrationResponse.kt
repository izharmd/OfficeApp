package com.bws.officeapp.registration.registrationmodel

data class ResgistrationResponse(
    val bStatus: Boolean,
    val `data`: List<DataRegistration>,
    val sMessage: String,
    val sStatus: String
)

data class DataRegistration(
    val Message: String
)