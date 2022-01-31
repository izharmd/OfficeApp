package com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param
import com.bws.officeapp.login.loginmodel.PramProjectStatusList

class ProjectStatusRepository(val api:ApiInterface,val pram:PramProjectStatusList,val pramProjectList: Param.PramProjectList) {

    suspend fun getProjectStatusList() = api.callProjectStatusList(pram)

    suspend fun getProjectList() = api.callProjectList(pramProjectList)
}