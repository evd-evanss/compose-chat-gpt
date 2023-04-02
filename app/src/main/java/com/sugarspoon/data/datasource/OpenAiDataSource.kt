package com.sugarspoon.data.datasource

import com.sugarspoon.data.exceptions.ChatGptExceptions
import com.sugarspoon.data.utils.Constants
import com.sugarspoon.data.model.GptRequest
import com.sugarspoon.data.model.GptResponse
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class OpenAiDataSource {

    private val openAiService = RetrofitServiceFactory(
        retrofitBuilder = Retrofit.Builder(),
        okHttpClient = OkHttpClient()
    ).newInstance<OpenAiService>()

    fun getCompletion(requestBody: GptRequest) = flow {
        try {
            emit(openAiService.getCompletion(requestBody))
        } catch (e: Exception) {
            throw ChatGptExceptions.extractException(e)
        }
    }

    interface OpenAiService {
        @Headers(
            Constants.CONTENT_TYPE,
            Constants.AUTHORIZATION
        )
        @POST(Constants.PATH_COMPLETIONS)
        suspend fun getCompletion(
            @Body requestBody: GptRequest
        ): GptResponse
    }
}