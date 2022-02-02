package com.bws.officeapp.timesheet.addproject.assignproject

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class AddProjectRepo(val api:ApiInterface,val pram:Param.PramAddProject) {
    suspend fun callAddProject() = api.callProjectAllocation(pram)
}