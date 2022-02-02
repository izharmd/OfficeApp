package com.bws.officeapp.timesheet.searchproject.searchprojectviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class SearchProjectRepo(val api:ApiInterface,val pram:Param.PramProjectList) {

    suspend fun callProjectList() = api.callProjectList(pram)
}