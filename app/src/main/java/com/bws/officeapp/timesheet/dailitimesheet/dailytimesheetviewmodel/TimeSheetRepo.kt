package com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.Api.Param

class TimeSheetRepo(val api:ApiInterface,val pram:Param.PramDailyTimeSheetDetails) {

    suspend fun callDailyTimeSheetDetails() = api.callDailyTimeSheetDetails(pram)

}