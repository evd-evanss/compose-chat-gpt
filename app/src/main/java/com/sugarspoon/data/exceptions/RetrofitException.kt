package com.sugarspoon.data.exceptions

import retrofit2.Response
import retrofit2.Retrofit

class RetrofitException constructor(
    message: String?,
    exception: Throwable?,
    private val type: Type
) : Throwable(message, exception){

    enum class Type {
        UNKNOWN, HTTP, NETWORK
    }

    internal var retrofit: Retrofit? = null
    internal var response: Response<*>? = null

    companion object {

        fun create(
            message: String?,
            exception: Throwable?,
            type: Type,
            retrofit: Retrofit? = null,
            response: Response<*>? = null
        ) : RetrofitException {
            return RetrofitException(message, exception, type).apply {
                this.response = response
                this.retrofit = retrofit
            }
        }
    }
}