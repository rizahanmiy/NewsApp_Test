package com.rizahanmiy.newsapp.utils.common

import com.rizahanmiy.newsapp.domain.common.ResultState
import retrofit2.HttpException

fun <T : Any> handleApiError(e: Throwable): ResultState<T> {
//    return ResultState.Error(throwable = e)
    return if (e is HttpException) {
        when {
            e.code().toString() == "error" -> {
                ResultState.NoAuthorization(message = 1, data = null)
            }
            e.code().toString() == "ok" -> {
                ResultState.NoAuthorization(message = 0, data = null)
            }
            else -> {
                ResultState.Error(throwable = e)
            }
        }
    } else {
        ResultState.Error(throwable = e)
    }
}

fun <T> handleApiSuccess(status: String?, data: T, totalResults: Int?): ResultState<T> {
    return when (status) {
        "ok" -> ResultState.Success(data, totalResults ?: 0)
        "error" -> ResultState.NoAuthorization(data, totalResults ?: 0)
        else -> ResultState.Failed(null, status ?: "error", totalResults ?: 0)
    }
}