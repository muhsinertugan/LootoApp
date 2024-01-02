package com.lotto.lottoapp.model.response

import com.lotto.lottoapp.model.response.error.ErrorResponse

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>() {
//        val user = ProfileScreenContract.UserData(
//            birthDate = "", city = CityResponseItem(
//                __v = 0,
//                _id = "",
//                code = 0,
//                latitude = "",
//                longitude = "",
//                name = "",
//                population = 0,
//                region = ""
//            ), email = "", lastName = "", name = "", phoneNumber = ""
//        )
    }

    data class Error(val code: Int, val response: ErrorResponse?) : ApiResponse<Nothing>()
}