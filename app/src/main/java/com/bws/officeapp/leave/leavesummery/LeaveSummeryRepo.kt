package com.bws.officeapp.leave.leavesummery

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class LeaveSummeryRepo(val api:ApiInterface,val pram:Param.PramUserLeaveSummary) {

    suspend fun getUserLeaveSummery() = api.callUserLeaveSummary(pram)
}