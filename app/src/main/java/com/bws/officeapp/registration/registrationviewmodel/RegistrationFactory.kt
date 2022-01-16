package com.bws.officeapp.registration.registrationviewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegistrationFactory(val registrationRepository: RegistrationRepository,val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationViewModel(registrationRepository,context) as T
    }

}