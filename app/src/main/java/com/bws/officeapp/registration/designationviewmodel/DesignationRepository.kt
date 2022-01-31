package com.bws.officeapp.registration.designationviewmodel

import com.bws.officeapp.Api.ApiInterface

class DesignationRepository(val api:ApiInterface,val designationPram: DesignationPram) {

    suspend fun getDesignationList() = api.callDesignationList(designationPram)
}