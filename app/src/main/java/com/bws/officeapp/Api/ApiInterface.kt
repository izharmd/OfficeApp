package com.bws.officeapp.Api

import com.bws.officeapp.leave.applyleave.applyviewmodel.ResponseApplyLeave
import com.bws.officeapp.leave.leaveapprove.leaveapproveviewmodel.LeaveApproveResponse
import com.bws.officeapp.leave.leavesummery.LeaveSummeryResponse
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusFactory
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusResponse
import com.bws.officeapp.login.loginmodel.LoginPram
import com.bws.officeapp.login.loginmodel.PramProjectStatusList
import com.bws.officeapp.login.loginmodel.ResponseLogin
import com.bws.officeapp.registration.designationviewmodel.DesignationPram
import com.bws.officeapp.registration.designationviewmodel.DesignationResponse
import com.bws.officeapp.registration.registrationmodel.PramRegistration
import com.bws.officeapp.registration.registrationmodel.ResgistrationResponse
import com.bws.officeapp.timesheet.addproject.assignproject.AddProjectResponse
import com.bws.officeapp.timesheet.addproject.userlistmodel.ResponseProjectList
import com.bws.officeapp.timesheet.addproject.userlistmodel.UserListResponse
import com.bws.officeapp.timesheet.dailitimesheet.dailytimesheetviewmodel.DailyTimeSheetResponse
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectListResponse
import com.bws.officeapp.timesheet.dailitimesheet.projectstatusviewmodel.ProjectStausListResponse
import com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel.ProjectStatusResponse
import com.bws.officeapp.timesheet.searchproject.projectalocationsearch.ProjectAllocationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("Userlogin")
    suspend fun callLoginApi(@Body loginPram: LoginPram): Response<ResponseLogin>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callDesignationList(@Body designationPram: DesignationPram): Response<DesignationResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("UserRegistration")
    suspend fun callRegistrationApi(@Body pramRegistration: PramRegistration): Response<ResgistrationResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callProjectStatusList(@Body pramProjectList: PramProjectStatusList): Response<ProjectStausListResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("DailyTimeSheetDetails")
    suspend fun callDailyTimeSheetDetails(@Body param: Param.PramDailyTimeSheetDetails): Response<DailyTimeSheetResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callProjectList(@Body pram:Param.PramProjectList):Response<ProjectListResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("ApplyLeave")
    suspend fun callApplyLeave(@Body pram:Param.PramApplyLeave):Response<ResponseApplyLeave>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("UserLeaveDetails")
    suspend fun callUserLeaveDetails(@Body pram:Param.PramUserLeaveDetails):Response<LeaveStatusResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("UserLeaveSummary")
    suspend fun callUserLeaveSummary(@Body pram:Param.PramUserLeaveSummary):Response<LeaveSummeryResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callUserList(@Body pram:Param.PramUserList):Response<UserListResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callProjectListForAddProject(@Body pram:Param.PramProjectList):Response<ResponseProjectList>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("PopulateDropDownList")
    suspend fun callLeaveApprove(@Body pram:Param.PramApproveLeave):Response<LeaveApproveResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("ProjectAllocation")
    suspend fun callProjectAllocation(@Body pram:Param.PramAddProject):Response<AddProjectResponse>


    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("UserProjectDetails")
    suspend fun callUserProjectDetails(@Body pram:Param.PramUserProjectDetails):Response<ProjectStatusResponse>

    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("ProjectAllocationSearch")
    suspend fun callProjectAllocationSearchByProjectName(@Body pram:Param.PramSearchByProjectName):Response<ProjectAllocationResponse>


    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("ProjectAllocationSearch")
    suspend fun callProjectAllocationSearchByProjectStatus(@Body pram:Param.PramSearchByProjectStatus):Response<ProjectAllocationResponse>



    @Headers("API_KEY:A862A321-15CA-4265-B188-3959E38A94D2")
    @POST("ProjectAllocationSearch")
    suspend fun callProjectAllocationSearchByProjectDateRange(@Body pram:Param.PramSearchByProjectDateRange):Response<ProjectAllocationResponse>


}