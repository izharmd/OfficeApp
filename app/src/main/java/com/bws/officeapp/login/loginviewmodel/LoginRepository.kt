package com.bws.officeapp.login.loginviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.login.loginmodel.LoginPram

class LoginRepository(val loginApi: ApiInterface, val loginPram: LoginPram) {


    suspend fun getUserLogin() = loginApi.callLoginApi(loginPram)
}