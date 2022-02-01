package com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class LeaveApproveRepo(val api:ApiInterface,val pram:Param.PramApproveLeave) {

    suspend fun getLeaveApprove() = api.callLeaveApprove(pram)
}