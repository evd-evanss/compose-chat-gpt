package com.sugarspoon.chatgpt.ui.chat

import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.sugarspoon.chatgpt.base.BaseViewModel
import com.sugarspoon.chatgpt.components.message.MessageModel
import com.sugarspoon.chatgpt.extensions.onCollect
import com.sugarspoon.data.datasource.OpenAiDataSource
import com.sugarspoon.data.repository.RepositoryImpl
import com.sugarspoon.data.utils.Constants
import com.sugarspoon.data.utils.Constants.CHAT_ERROR_MESSAGE
import com.sugarspoon.data.utils.Constants.CHAT_LABEL_USER
import java.util.Calendar
import java.util.Locale
import kotlinx.coroutines.launch

class ChatViewModel: BaseViewModel<ChatState, ChatEvents>(ChatState()) {

    private val repository = RepositoryImpl(OpenAiDataSource())
    private val messages = mutableListOf<MessageModel>()
    private val calendar = Calendar.getInstance(Locale.getDefault())

    @RequiresApi(Build.VERSION_CODES.O)
    override fun reduce(oldState: ChatState, sideEffect: ChatEvents) {
        when(sideEffect) {
            is ChatEvents.OnWriteQuestion -> {
                createNewState(
                    newState = oldState.copy(
                        question = sideEffect.question
                    )
                )
            }
            is ChatEvents.OnSendQuestion -> {
                includeQuestion(question = oldState.question, isResponse = false)
                createNewState(
                    newState = oldState.copy(
                        loading = true,
                        messages = messages
                    )
                )
                sendQuestion(
                    question = oldState.question,
                    onSuccess = {
                        it.forEach { message ->
                            messages.add(message)
                        }
                        createNewState(
                            newState = oldState.copy(
                                messages = messages,
                                loading = false,
                                question = ""
                            )
                        )
                    },
                    onError = {
                        includeQuestion(question = CHAT_ERROR_MESSAGE, isResponse = true)
                        createNewState(
                            newState = oldState.copy(
                                messages = messages,
                                loading = false,
                                question = ""
                            )
                        )
                    }
                )
            }
        }
    }

    private fun includeQuestion(question: String, isResponse: Boolean = false) {
        messages.add(
            MessageModel(
                text = question,
                name = CHAT_LABEL_USER,
                isResponse = isResponse,
                time = getTimeFormatted()
            )
        )
    }

    private fun sendQuestion(
        question: String,
        onSuccess: (List<MessageModel>) -> Unit,
        onError: (Throwable) -> Unit
    ) = viewModelScope.launch {
        repository.getCompletion(question).onCollect(
            onSuccess = onSuccess,
            onError = onError
        )
    }

    fun onSendClicked() {
        emitEvent(ChatEvents.OnSendQuestion)
    }

    fun onWriteQuestion(question: String) {
        emitEvent(ChatEvents.OnWriteQuestion(question))
    }

    private fun getTimeFormatted() = DateFormat.format(Constants.TIME_FORMAT, calendar).toString()
}