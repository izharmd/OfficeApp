package com.bws.officeapp.Api

class Param {
    data class PramDailyTimeSheetDetails(
        val UserID: String,
        val ProjectStatusID: String,
        val ProjectID: String,
        val Date: String,
        val DateStartTime: String,
        val DateEndTime: String,
        val TotalWorkingTime: String,
        val OverTime: String
    )

    data class PramProjectList(val Type: String)

    //APPLY LEAVE
    data class PramApplyLeave(
        val UserID: String,
        val LeaveID: String,
        val LeaveCategoryID: String,
        val LeaveFrom: String,
        val IsLeaveFromHalfDay: String,
        val LeaveTo: String,
        val IsLeaveToHalfDay: String,
        val LeaveReason: String,
        val IsActive: String
    )

    //LEAVE APPLICATION DETAILS

    data class PramUserLeaveDetails(
        val UserID: String,
        val PageSize: String,
        val CurrentPageNo: String
    )

    //LEAVE SUMMERY

    data class PramUserLeaveSummary(val UserID:String,val Year:String)
}
