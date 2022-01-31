package com.bws.officeapp.registration.designationviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DesignationFactory(val designationRepository: DesignationRepository,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DesignationViewModel(designationRepository,context) as T
    }
}