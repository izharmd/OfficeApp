package com.bws.officeapp.leavestatus

data class LeaveStatusModel(
    val empid: String,
    val empName: String,
    val dayOfleave: String,
    val leaveFrom: String,
    val leaveTo: String,
    val leaveStatus: String,
    val Reason: String
)
