package com.bws.officeapp.timesheet.searchproject.projectalocationsearch

import android.content.Context
import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class ProjectAllocationRepo(val api:ApiInterface,val pram:Param.PramSearchByProjectName) {

    suspend fun callProjectAllocationByProjectName() = api.callProjectAllocationSearchByProjectName(pram)
}

class ProjectAllocationRepoByProjectStatus(val api:ApiInterface,val pram:Param.PramSearchByProjectStatus) {
    suspend fun callProjectAllocationByProjectStatus() = api.callProjectAllocationSearchByProjectStatus(pram)
}

class ProjectAllocationRepoByProjectDateRange(val api:ApiInterface,val pram:Param.PramSearchByProjectDateRange) {
    suspend fun callProjectAllocationByProjectDateRange() = api.callProjectAllocationSearchByProjectDateRange(pram)
}