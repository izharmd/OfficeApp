package com.bws.officeapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrogitHelper {
   // private const val BASE_URL = "https://quotable.io/"
   private const val BASE_URL = "http://i-plus.co.in/mock/api/mockexamservice/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}