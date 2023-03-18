package com.sugarspoon.data.repository

import android.text.format.DateFormat
import com.sugarspoon.chatgpt.components.message.MessageModel
import com.sugarspoon.data.datasource.OpenAiDataSource
import com.sugarspoon.data.model.GptRequest
import com.sugarspoon.data.utils.Constants
import com.sugarspoon.data.utils.Constants.MAX_TOKENS
import com.sugarspoon.data.utils.Constants.REQUEST_MODEL
import java.util.Calendar
import java.util.Locale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface OpenAinRepository {
    fun getCompletion(question: String): Flow<List<MessageModel>>
}

class RepositoryImpl(
    private val dataSource: OpenAiDataSource
) : OpenAinRepository {

    private val calendar = Calendar.getInstance(Locale.getDefault())

    override fun getCompletion(question: String) = dataSource.getCompletion(
        GptRequest(
            prompt = question,
            max_tokens = MAX_TOKENS,
            model = REQUEST_MODEL
        )
    ).map {
        val messages = mutableListOf<MessageModel>()
        it.choices.map { choice ->
            messages.add(
                MessageModel(
                    text = choice.text.trim(),
                    isResponse = true,
                    name = Constants.CHAT_GPT,
                    time = DateFormat.format(Constants.TIME_FORMAT, calendar).toString()
                )
            )
        }
        messages.toList()
    }
}

