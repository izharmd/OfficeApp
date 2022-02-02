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

    data class PramUserLeaveSummary(val UserID: String, val Year: String)

    // APPROVE LEAVE
    data class PramApproveLeave(
        val UserID: String,
        val LeaveID: String,
        val IsApproved: String,
        val ApprovalNotes: String
    )

    // USER LIST
    data class PramUserList(val Type: String)

    //ADD PROJECT
    data class PramAddProject(
        val AgreedDeliveryDate: String,
        val AgreedTime: String,
        val Allocatedlist: List<Allocatedlist>,
        val ProjectID: String,
        val StartDate: String,
        val UserID: String
    )

    data class Allocatedlist(
        val UserID_Fk: String
    )

    // PROJECT STATUS LIST
    data class PramUserProjectDetails(
        val UserID: String,
        val PageSize: String,
        val CurrentPageNo: String
    )


    // SEARCH BY PROJECT NAME
    data class PramSearchByProjectName(
        val UserID: String,
        val SearchType: String,
        val ProjectName: String,
        val PageSize: String,
        val CurrentPageNo: String
    )

    // SEARCH BY PROJECT STATUS
    data class PramSearchByProjectStatus(
        val UserID: String,
        val SearchType: String,
        val ProjectStatus: String,
        val PageSize: String,
        val CurrentPageNo: String
    )

    // SEARCH BY PROJECT DATE RANGE
    data class PramSearchByProjectDateRange(
        val UserID: String,
        val SearchType: String,
        val FromDate: String,
        val ToDate: String,
        val PageSize: String,
        val CurrentPageNo: String
    )
}

