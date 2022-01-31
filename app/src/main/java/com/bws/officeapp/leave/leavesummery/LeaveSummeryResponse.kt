package com.bws.officeapp.leave.leavesummery

data class LeaveSummeryResponse(
    val bStatus: Boolean,
    val `data`: Data,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val RemainingLeave: String,
    val TotalAllocatedLeave: String
)