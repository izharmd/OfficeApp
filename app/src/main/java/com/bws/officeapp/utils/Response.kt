package com.bws.officeapp.utils

sealed class Response<T>(
    val data: T? = null,
    val loadingMessage: String? = null,
    val errorMessage: String? = null,
    val noInternetMessage: String? = null
) {

    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Loading<T>(loadingMessage: String) : Response<T>(loadingMessage = loadingMessage)
    class Error<T>(errorMessage: String): Response<T>(errorMessage = errorMessage)
    class NoInternet<T>(noInternetMessage: String):Response<T>(noInternetMessage = noInternetMessage)
}
