package com.lotto.lottoapp.ui.constants

data class ErrorCode(
    val code: Int,
    val message: String,
)

object ErrorCodes {
    val EMAIL_REQUIRED = ErrorCode(2009, "Email is required.")
    val EMAIL_FORMAT_INVALID = ErrorCode(2010, "Email format is invalid.")
    val NAME_REQUIRED = ErrorCode(2011, "Name is required.")
    val LAST_NAME_REQUIRED = ErrorCode(2012, "Last name is required.")
    val PHONE_NUMBER_REQUIRED = ErrorCode(2013, "Phone number is required.")
    val PHONE_NUMBER_FORMAT_INVALID = ErrorCode(2014, "Phone number format is invalid.")
    val OTP_REQUIRED = ErrorCode(2015, "Otp is required.")
    val OTP_INVALID = ErrorCode(2016, "Otp is invalid.")
    val OTP_EXPIRED = ErrorCode(2017, "Otp is expired.")
    val OTP_NOT_FOUND = ErrorCode(2018, "Otp not found.")
    val OTP_NOT_MATCH = ErrorCode(2019, "Otp not match.")
    val OTP_SERVICE_ERROR = ErrorCode(2099, "Otp service error. Please try again later.")
    val PASSWORD_REQUIRED = ErrorCode(2020, "Password is required.")
    val PASSWORD_FORMAT_INVALID = ErrorCode(2021, "Password format is invalid.")
    val PASSWORD_NOT_MATCH = ErrorCode(2022, "Password not match.")
    val USER_NOT_FOUND = ErrorCode(2023, "User not found.")
    val USER_ALREADY_EXIST = ErrorCode(2024, "User already exists.")
    val USER_NOT_VERIFIED = ErrorCode(2025, "User not verified.")
    val USER_NOT_ACTIVE = ErrorCode(2026, "User not active.")
    val UNAUTHORIZED = ErrorCode(2027, "Unauthorized.")
    val EMAIL_OR_PHONE_ALREADY_EXIST = ErrorCode(2028, "Email or phone number already exists.")
    val CITY_ID_REQUIRED = ErrorCode(2029, "City is required.")
    val INTERNAL_SERVER_ERROR = ErrorCode(2099, "Internal server error.")
    val AMOUNT_REQUIRED = ErrorCode(2030, "Amount is required in the request body.")
    val AMOUNT_MUST_BE_NUMBER = ErrorCode(2031, "Amount must be a number.")
    val AMOUNT_MUST_BE_POSITIVE = ErrorCode(2032, "Amount must be positive.")
    val EMAIL_NOT_ALLOWED = ErrorCode(2033, "Email update not allowed.")
    val BALANCE_NOT_ALLOWED = ErrorCode(2034, "Balance update not allowed.")
    val GAME_NOT_FOUND = ErrorCode(2035, "Game not found.")
    val SERVER_ERROR = ErrorCode(2099, "General server error. Report to admin.")
    val BLOCK_NUMBERS_COUNT_NOT_MATCH =
        ErrorCode(2036, "Numbers on block must be equal to required numbers.")
    val INVALID_NUMBERS = ErrorCode(
        2037,
        "Invalid numbers. Numbers must be between the minimum and maximum numbers of the game."
    )
    val INSUFFICIENT_BALANCE = ErrorCode(2038, "Insufficient balance.")
}
