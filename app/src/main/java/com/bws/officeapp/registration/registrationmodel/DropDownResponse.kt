package com.bws.officeapp.registration.registrationmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class DropDownResponse(
    val bStatus: Boolean,
    val `data`: Data,
    val sMessage: String,
    val sStatus: String
)

data class Data(
    val ProjectList: List<Project>,
    val ProjectStatusList: List<ProjectStatus>,
    val UserDesignationList: List<UserDesignation>
)

data class Project(
    val ProjectID: Int,
    val ProjectName: String
)

data class ProjectStatus(
    val ProjectStatus: String,
    val ProjectStatusID: Int
)

data class UserDesignation(
    val RoleID: Int,
    val RoleName: String
)

