package com.lotto.lottoapp.utils

import com.google.gson.Gson
import com.lotto.lottoapp.model.response.ApiResponse
import com.lotto.lottoapp.model.response.error.ErrorResponse
import retrofit2.Response


fun <T> handleResponse(response: Response<T>): ApiResponse<T> {
    return try {
        if (response.isSuccessful) {
            val body: T? = response.body()
            body?.let {
                ApiResponse.Success(it)
            } ?: ApiResponse.Error(
                code = response.code(), response = ErrorResponse(
                    code = response.code(),
                    message = "Null response body",
                    success = false
                )
            )
        } else {
            val errorBody = response.errorBody()
                ?.let { Gson().fromJson(it.string(), ErrorResponse::class.java) }
            ApiResponse.Error(response.code(), errorBody)
        }
    } catch (e: Exception) {
        ApiResponse.Error(
            500, ErrorResponse(
                code = response.code(),
                message = "Internal Server Error",
                success = false
            )
        )
    }
}
