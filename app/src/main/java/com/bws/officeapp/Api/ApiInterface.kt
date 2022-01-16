package com.bws.officeapp.Api

import com.bws.officeapp.login.loginmodel.LoginPram
import com.bws.officeapp.login.loginmodel.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("UserloginDetails")
    suspend fun callLoginApi(@Body loginPram: LoginPram): Response<ResponseLogin>
}