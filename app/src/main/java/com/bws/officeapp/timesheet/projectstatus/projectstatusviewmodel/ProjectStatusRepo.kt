package com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class ProjectStatusRepo(val api:ApiInterface,val pram:Param.PramUserProjectDetails) {

    suspend fun callUserProjectDetails() = api.callUserProjectDetails(pram)
}