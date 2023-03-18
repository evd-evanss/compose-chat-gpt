package com.sugarspoon.chatgpt.extensions

import com.google.gson.Gson
import com.sugarspoon.data.exceptions.RetrofitException
import com.sugarspoon.data.model.ErrorResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

fun <T> Flow<T>.onCollect(
    onSuccess:(suspend (t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null,
    onLoading: ((isLoading: Boolean) -> Unit)? = null,
    coroutineScope: CoroutineScope = CoroutineScope(IO)
) {
    coroutineScope.launch {
        withContext(Main) {
            onLoading?.invoke(true)
        }
        try {
            collect { result ->
                onSuccess?.let {
                    withContext(Main) {
                        it(result)
                        onLoading?.invoke(false)
                    }
                }
            }
        } catch (e: Throwable) {
            withContext(Main) {
                if (e is HttpException) {
                    val json = e.response()?.errorBody()?.string()
                    try {
                        val message = Gson().fromJson(json, ErrorResponse::class.java)
                        onError?.invoke(RetrofitException.create(message.message, e, RetrofitException.Type.HTTP))
                    } catch (e: Exception) {
                        onError?.invoke(e)
                    }
                } else {
                    onError?.invoke(e)
                }
                onLoading?.invoke(false)
            }
        }
    }

}