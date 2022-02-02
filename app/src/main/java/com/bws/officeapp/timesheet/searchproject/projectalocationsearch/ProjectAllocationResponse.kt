package com.bws.officeapp.timesheet.searchproject.projectalocationsearch

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ProjectAllocationResponse(
    val bStatus: Boolean,
    val `data`: Data,
    val sMessage: String,
    val sStatus: String
)


data class Project(
    val AgreedDeliveryDate: String,
    val AgreedTime: Int,
    val AllocatedBy: String,
    val CreatedBy: Int,
    val ProjectName: String,
    val ProjectStatus: String,
    val SlNo: Int,
    val StartDate: String,
    val TotalTime: Int,
    val UserID: Int
)


data class Page(
    val CurrentPageNo: Int,
    val TotalPageCount: String,
    val TotalRecordsCount: String
)

data class Data(
    val Page: List<Page>,
    val ProjectList: List<Project>
)