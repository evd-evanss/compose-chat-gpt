package com.sugarspoon.data.datasource

import com.sugarspoon.data.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitServiceFactory(
    val retrofitBuilder: Retrofit.Builder,
    val okHttpClient: OkHttpClient
) {

    inline fun <reified Service> newInstance(): Service {
        return retrofitBuilder
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(Service::class.java)
    }
}