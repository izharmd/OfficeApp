package com.bws.officeapp.leavestatus.leavestatusviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class LeaveStatusRepo(val api:ApiInterface,val pram:Param.PramUserLeaveDetails) {
    suspend fun getLeaveStatusDetails() = api.callUserLeaveDetails(pram)
}