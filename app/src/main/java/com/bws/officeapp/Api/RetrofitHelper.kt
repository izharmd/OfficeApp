package com.bws.officeapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
   // private const val BASE_URL = "https://quotable.io/"
   private const val BASE_URL = "http://bitwavesolutions.co.uk/OfficeApp/api/service/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}