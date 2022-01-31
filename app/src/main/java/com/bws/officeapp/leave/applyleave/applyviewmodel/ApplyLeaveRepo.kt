package com.bws.officeapp.leave.applyleave.applyviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class ApplyLeaveRepo(val api:ApiInterface,val pramApplyLeave: Param.PramApplyLeave) {
    suspend fun callApplyLeave() = api.callApplyLeave(pramApplyLeave)
}