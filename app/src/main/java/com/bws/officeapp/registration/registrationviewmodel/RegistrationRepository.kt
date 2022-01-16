package com.bws.officeapp.registration.registrationviewmodel

import com.bws.officeapp.Api.ApiInterface
import com.bws.officeapp.registration.registrationmodel.PramRegistration

class RegistrationRepository(val api: ApiInterface, val pramRegistration: PramRegistration) {
    suspend fun getUserRegistration() = api.callRegistrationApi(pramRegistration)
}