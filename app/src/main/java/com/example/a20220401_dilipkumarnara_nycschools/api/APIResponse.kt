package com.dish.celltech.api

import okhttp3.ResponseBody
/*
* A common class for API response
* */
sealed class APIResponse<T>(
    val status: Status,
    val data: T? = null,
    val error: ResponseBody? = null,
    val message: String? = null) {

    enum class Status {
        EMPTY,
        LOADING,
        SUCCESS,
        ERROR
    }
    
    class Empty<T>() : APIResponse<T>(Status.EMPTY)
    class Loading<T>() : APIResponse<T>(Status.LOADING)
    class Success<T>(data: T) : APIResponse<T>(Status.SUCCESS,data = data)
    class Error<T>(message: String, error: ResponseBody? = null) : APIResponse<T>(Status.ERROR, error = error, message = message)
}
