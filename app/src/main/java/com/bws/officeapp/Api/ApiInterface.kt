package com.bws.officeapp.Api

import com.bws.officeapp.login.loginmodel.LoginPram
import com.bws.officeapp.login.loginmodel.ResponseLogin
import com.bws.officeapp.registration.registrationmodel.PramRegistration
import com.bws.officeapp.registration.registrationmodel.ResgistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("UserloginDetails")
    suspend fun callLoginApi(@Body loginPram: LoginPram): Response<ResponseLogin>

    @POST("UserRegistrationDetails")
    suspend fun callRegistrationApi(@Body pramRegistration: PramRegistration):Response<ResgistrationResponse>
}