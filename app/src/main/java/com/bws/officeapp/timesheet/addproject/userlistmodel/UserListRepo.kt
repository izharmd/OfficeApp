package com.bws.officeapp.timesheet.addproject.userlistmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class UserListRepo(val api:ApiInterface,val pram:Param.PramUserList,val pramProjectList:Param.PramProjectList) {

    suspend fun callUserList() = api.callUserList(pram)
    suspend fun callProjectListForAppProject() = api.callProjectListForAddProject(pramProjectList)
}